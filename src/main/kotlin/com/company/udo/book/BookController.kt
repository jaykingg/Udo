package com.company.udo.book

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/book")
class BookController {

    @PostMapping("/entrust")
    fun entrustBook() {

    }

    @GetMapping("/list")
    fun getBookList() {

    }

    @PostMapping("/rent")
    fun rentBooks() {

    }

    @PostMapping("/return")
    fun returnBooks() {

    }
}