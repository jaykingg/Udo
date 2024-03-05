package com.company.udo.rental

import com.company.udo.book.Book
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RentalRepository : JpaRepository<Rental, Long> {
    fun findByBookAndReturnedAtIsNull(book: Book): Optional<Rental>
}