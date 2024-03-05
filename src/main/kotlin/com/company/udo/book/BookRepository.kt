package com.company.udo.book

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, Long> {
    @Query("SELECT b, COUNT(r) as rentalCount FROM Book b LEFT JOIN b.rentals r GROUP BY b ORDER BY rentalCount DESC")
    fun findAllBooksAndSortByRentalCount(pageable: Pageable): Page<Book>
}