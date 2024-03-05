package com.company.udo.account.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AccountRegisterPayload(
    @field: NotBlank(message = "이름은 필수 입력 값입니다.")
    val name: String,

    @field: NotBlank(message = "이메일은 필수 입력 값입니다.")
    @field: Email(message = "이메일 형식에 맞지 않습니다.")
    val email: String,

    @field: NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    @field: Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다.")
    val mobileNumber: String,

    @field: NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    //TODO
//    @field: Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$", message = "비밀번호 양식과 맞지 않습니다.")
    val password: String
)
