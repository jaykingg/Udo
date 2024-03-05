package com.company.udo.book

import com.company.udo.account.Account
import com.company.udo.rental.Rental
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

@Entity
@Table(name = "Book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field: NotBlank
    val bookName: String,

    @field: NotBlank
    val isbn: String,

    @field: Min(0)
    val price: Int,

    @field: ManyToOne(fetch = FetchType.LAZY)
    @field: JoinColumn(name = "account_id")
    val account: Account,

    val registeredAt: LocalDateTime = LocalDateTime.now(),
    
    @field: OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    val rentals: List<Rental> = mutableListOf()

)