package com.company.udo.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtTokenFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    override fun doFilter(req: ServletRequest, res: ServletResponse, filterChain: FilterChain) {
        val token = jwtTokenProvider.resolveToken(req as HttpServletRequest)
        if (token != null && jwtTokenProvider.validateToken(token)) {
            val auth = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = auth
        }
        filterChain.doFilter(req, res)
    }
}

