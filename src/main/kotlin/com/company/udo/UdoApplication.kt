package com.company.udo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class UdoApplication

fun main(args: Array<String>) {
    runApplication<UdoApplication>(*args)
}
