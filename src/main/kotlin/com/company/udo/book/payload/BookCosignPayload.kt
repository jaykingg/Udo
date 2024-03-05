package com.company.udo.book.payload

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.ISBN

data class BookCosignPayload(
    @field: NotBlank(message = "도서명은 필수 값입니다.")
    val bookName: String,

    @field: NotBlank(message = "ISBN 코드는 필수 값입나다.")
    @field: ISBN
    val isbn: String,

    @field: Min(0)
    val price: Int
)