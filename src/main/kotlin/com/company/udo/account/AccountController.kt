package com.company.udo.account

import com.company.udo.account.payload.AccountLoginPayload
import com.company.udo.account.payload.AccountRegisterPayload
import com.company.udo.account.response.TokenResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody payload: AccountRegisterPayload
    ): ResponseEntity<Void> {
        accountService.register(payload)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody payload: AccountLoginPayload
    ): ResponseEntity<TokenResponse> {
        val token = accountService.login(payload)
        return ResponseEntity.ok(TokenResponse(token = token))
    }
}