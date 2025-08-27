package com.ggardet

import org.springframework.boot.runApplication
import org.springframework.modulith.Modulith
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@Modulith
@EnableAsync
@EnableScheduling
class SpringModulithApplication

fun main(args: Array<String>) {
    runApplication<SpringModulithApplication>(*args)
}
