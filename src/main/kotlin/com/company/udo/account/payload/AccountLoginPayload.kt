package com.company.udo.account.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AccountLoginPayload(
    @field: NotBlank(message = "이메일은 필수 입력 값입니다.")
    @field: Email(message = "이메일 형식에 맞지 않습니다.")
    val email: String,

    @field: NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @field: Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$", message = "비밀번호 양식과 맞지 않습니다.")
    val password: String
)