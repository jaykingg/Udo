package com.company.udo.book.payload

import jakarta.validation.constraints.NotEmpty

data class BookRentPayload(
    @field: NotEmpty
    val rentBookIds: List<Long>
)