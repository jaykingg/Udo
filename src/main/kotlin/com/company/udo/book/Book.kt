package com.company.udo.book

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "Book")
data class Book(
    @Id
    val id: Long,

    @field: NotBlank
    val bookName: String,

    @field: NotBlank
    val ibsnCode: String,

    @field: Min(0)
    val price: Int,
    
    val enable: Boolean = false

)