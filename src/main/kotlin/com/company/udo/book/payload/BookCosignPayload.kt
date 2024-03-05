package com.company.udo.book.payload

data class BookCosignPayload(
    val bookName: String,

    val isbn: String,

    val price: Int
)