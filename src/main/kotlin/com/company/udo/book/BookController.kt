package com.company.udo.book

import com.company.udo.book.payload.BookCosignPayload
import com.company.udo.book.payload.BookRentPayload
import com.company.udo.book.response.BookResponse
import com.company.udo.rental.response.RentalResponse
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/book")
class BookController(
    private val bookService: BookService
) {

    @PostMapping("/consign")
    fun consignBook(
        @Valid @RequestBody bookCosignPayload: BookCosignPayload,
        principal: Principal
    ): ResponseEntity<BookResponse> {
        return ResponseEntity.ok(bookService.consignBook(bookCosignPayload, principal))
    }

    @GetMapping("/list")
    fun getBookList(
        pageable: Pageable
    ): ResponseEntity<Page<BookResponse>> {
        return ResponseEntity.ok(bookService.getBookList(pageable))
    }

    @PostMapping("/rent")
    fun rentBooks(
        @Valid @RequestBody bookRentPayload: BookRentPayload,
        principal: Principal
    ): ResponseEntity<List<RentalResponse>> {
        return ResponseEntity.ok(bookService.rentBooks(bookRentPayload, principal))
    }
}