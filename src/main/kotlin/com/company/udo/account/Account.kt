package com.company.udo.account

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(
    name = "Account", uniqueConstraints = [
        UniqueConstraint(columnNames = ["email"], name = "uniqueEmailConstraint")
    ]
)
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field: NotBlank(message = "이름은 필수 입력 값입니다.")
    val name: String,

    @field: NotBlank(message = "이메일은 필수 입력 값입니다.")
    @field: Email(message = "이메일 형식에 맞지 않습니다.")
    val email: String,

    @field: NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    val mobileNumber: String,

    @field: NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    val password: String

)