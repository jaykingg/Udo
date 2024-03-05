package com.company.udo.security

import com.company.udo.account.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    @Autowired private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account =
            accountRepository.findByEmail(username) ?: throw UsernameNotFoundException("유저 정보를 찾을 수 없습니다.")

        return User.builder()
            .username(account.email)
            .password(account.password)
            .authorities(emptyList())
            .build()
    }
}
