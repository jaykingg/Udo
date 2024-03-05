package com.company.udo.book.response

import com.company.udo.account.response.AccountResponse
import com.company.udo.book.Book
import java.time.LocalDateTime

data class BookResponse(
    val id: Long,
    val bookName: String,
    val isbn: String,
    val price: Int,
    val registeredAt: LocalDateTime,
    val accountResponse: AccountResponse? = null,
    val isRented: Boolean,
    val rentalCount: Int
)

fun convertToBookResponse(book: Book): BookResponse {
    return BookResponse(
        id = book.id,
        bookName = book.bookName,
        isbn = book.isbn,
        price = book.price,
        registeredAt = book.registeredAt,
        isRented = false,
        rentalCount = 0
    )
}