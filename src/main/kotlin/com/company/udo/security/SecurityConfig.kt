package com.company.udo.security

import org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    //private val userDetailsService: UserDetailsService,
    //private val jwtRequestFilter: JwtRequestFilter
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests()
            .requestMatchers(toH2Console()).permitAll()
            .requestMatchers("/api/auth/login", "/api/users/register").permitAll()
            .requestMatchers("/api/products/**").authenticated()
            .anyRequest().authenticated()
            .and()
            .headers().frameOptions().disable()
            .and()
            .csrf().ignoringRequestMatchers(toH2Console()).disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
