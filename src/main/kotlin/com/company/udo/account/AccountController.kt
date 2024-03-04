package com.company.udo.account

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/register")
    fun register() {

    }

    @PostMapping("/login")
    fun login() {

    }
}