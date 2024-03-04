package com.company.udo.Account

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Account")
data class Account(
    @Id
    val id: Long,

    val name: String = "test_name"
)