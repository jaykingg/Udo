package com.company.udo.account

import com.company.udo.account.payload.AccountLoginPayload
import com.company.udo.account.payload.AccountRegisterPayload
import com.company.udo.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun register(payload: AccountRegisterPayload) {
        if (accountRepository.existsByEmail(payload.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일이 이미 사용 중입니다.")
        }

        val account = Account(
            name = payload.name,
            email = payload.email,
            mobileNumber = payload.mobileNumber,
            password = passwordEncoder.encode(payload.password)
        )
        accountRepository.save(account)
    }

    fun login(payload: AccountLoginPayload): String {
        val account = accountRepository.findByEmail(payload.email)
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 정보가 잘못되었습니다.")

        if (!passwordEncoder.matches(payload.password, account.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.")
        }

        return jwtTokenProvider.createToken(account.email, listOf("USER"))
    }
}