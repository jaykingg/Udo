package com.company.udo.book.payload

import jakarta.validation.constraints.NotBlank

data class BookRentPayload(
    @field: NotBlank
    val rentBookIds: List<Long>
)