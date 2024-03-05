package com.company.udo.book

import com.company.udo.account.AccountRepository
import com.company.udo.account.response.AccountResponse
import com.company.udo.book.payload.BookCosignPayload
import com.company.udo.book.payload.BookRentPayload
import com.company.udo.book.response.BookResponse
import com.company.udo.book.response.convertToBookResponse
import com.company.udo.rental.Rental
import com.company.udo.rental.RentalRepository
import com.company.udo.rental.response.RentalResponse
import com.company.udo.rental.response.convertToRentalResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.security.Principal
import java.time.Instant
import java.time.LocalDateTime

@Service
class BookService(
    private val accountRepository: AccountRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository,
    private val taskScheduler: TaskScheduler,
) {
    @Transactional
    fun consignBook(payload: BookCosignPayload, principal: Principal): BookResponse {
        val account = accountRepository.findByEmail(principal.name) ?: throw ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "로그인 정보가 잘못되었습니다."
        )
        return convertToBookResponse(
            bookRepository.save(
                Book(
                    bookName = payload.bookName,
                    isbn = payload.isbn,
                    price = payload.price,
                    account = account
                )
            )
        )
    }

    fun getBookList(pageable: Pageable, sortType: String): Page<BookResponse> {
        val sortedPageable = when (sortType) {
            "rentalCount" -> PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by("rentalCount").descending())
            "price" -> PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by("price").ascending())
            "recent" -> PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by("registeredAt").descending())
            else -> pageable
        }

        val books = bookRepository.findAll(sortedPageable)
        return books.map { book ->
            BookResponse(
                id = book.id,
                bookName = book.bookName,
                isbn = book.isbn,
                price = book.price,
                registeredAt = book.registeredAt,
                accountResponse = AccountResponse(
                    id = book.account.id,
                    name = book.account.name
                ),
                isRented = book.rentals.any { rental -> rental.returnedAt == null },
                rentalCount = book.rentals.size
            )
        }
    }

    @Transactional
    fun rentBooks(payload: BookRentPayload, principal: Principal): List<RentalResponse> {
        val account = accountRepository.findByEmail(principal.name) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "사용자를 찾을 수 없습니다."
        )
        val rentals = mutableListOf<Rental>()

        payload.rentBookIds.forEach { bookId ->
            val book = bookRepository.findById(bookId)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "해당 도서를 찾을 수 없습니다.") }
            if (rentalRepository.findByBookAndReturnedAtIsNull(book).isEmpty) {
                val rental = Rental(book = book, rentedBy = account)
                rentals.add(rentalRepository.save(rental).also {
                    scheduleReturn(rental.id)
                })
            }
        }

        return rentals.map { convertToRentalResponse(it) }
    }

    fun scheduleReturn(bookId: Long) {
        val delay = (15000..20000).random().toLong()
        taskScheduler.schedule({
            returnBook(bookId)
        }, Instant.now().plusMillis(delay))
    }

    @Transactional
    fun returnBook(rentalId: Long) {
        rentalRepository.findById(rentalId).takeIf { it.isPresent && it.get().returnedAt == null }?.let { rental ->
            rental.get().returnedAt = LocalDateTime.now()
            rentalRepository.save(rental.get())
        }
    }
}