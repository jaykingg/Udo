package com.company.udo.rental.response

import com.company.udo.rental.Rental
import java.time.LocalDateTime

data class RentalResponse(
    val id: Long,
    val bookName: String,
    val rentedAt: LocalDateTime
)

fun convertToRentalResponse(rental: Rental): RentalResponse {
    return RentalResponse(
        id = rental.id,
        bookName = rental.book.bookName,
        rentedAt = rental.rentedAt
    )
}