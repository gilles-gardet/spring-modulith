package com.ggardet

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

@TestConfiguration(proxyBeanMethods = false)
class TestContainersConfiguration {
    
    @Bean
    @ServiceConnection
    fun postgreSQLContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer("postgres:15.4-alpine3.18")
            .withDatabaseName("modularmonolith")
            .withUsername("modularmonolith")
            .withPassword("modularmonolith")
    }
}