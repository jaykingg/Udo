package com.company.udo.book.payload

import jakarta.validation.constraints.NotBlank

data class BookRentPayload(
    @field: NotBlank(message = "반납 도서 아이디가 있어야합니다.")
    val rentBookIds: List<Long>
)