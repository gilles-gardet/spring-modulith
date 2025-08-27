package com.ggardet.core.configuration

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.stereotype.Component

@Component
class MultiModuleFlywayMigrationStrategy : FlywayMigrationStrategy {

    override fun migrate(flyway: Flyway) {
        val dataSource = flyway.configuration.dataSource
        
        val patientModule = Flyway.configure()
            .schemas("patients")
            .createSchemas(true)
            .locations("db/migration/patient")
            .dataSource(dataSource)
            .load()
        patientModule.migrate()
        
        val serviceModule = Flyway.configure()
            .schemas("services")
            .createSchemas(true)
            .locations("db/migration/service")
            .dataSource(dataSource)
            .load()
        serviceModule.migrate()
        
        val hospitalModule = Flyway.configure()
            .schemas("hospitals")
            .createSchemas(true)
            .locations("db/migration/hospital")
            .dataSource(dataSource)
            .load()
        hospitalModule.migrate()
        
        val doctorModule = Flyway.configure()
            .schemas("doctors")
            .createSchemas(true)
            .locations("db/migration/doctor")
            .dataSource(dataSource)
            .load()
        doctorModule.migrate()
        
        val eventModule = Flyway.configure()
            .createSchemas(true)
            .locations("db/migration/event")
            .dataSource(dataSource)
            .load()
        eventModule.migrate()
    }
}