package com.company.udo.book.payload

import jakarta.validation.constraints.NotBlank

data class BookReturnPayload(
    @field: NotBlank
    val returnBookIds: List<Long>
)