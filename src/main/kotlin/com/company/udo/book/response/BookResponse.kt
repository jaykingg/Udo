package com.company.udo.book.response

import com.company.udo.account.response.AccountResponse
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