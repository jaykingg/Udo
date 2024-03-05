package com.company.udo.rental

import com.company.udo.account.Account
import com.company.udo.book.Book
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Rental")
data class Rental(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field: ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    val book: Book,

    @field: ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val rentedBy: Account,

    val rentedAt: LocalDateTime = LocalDateTime.now(),

    var returnedAt: LocalDateTime? = null
)