package com.ggardet.core.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

@Component
public class MultiModuleFlywayMigrationStrategy implements FlywayMigrationStrategy {

    @Override
    public void migrate(final Flyway flyway) {
        final var dataSource = flyway.getConfiguration().getDataSource();
        final var patientModule = Flyway.configure()
                .schemas("patients")
                .createSchemas(true)
                .locations("db/migration/patient")
                .dataSource(dataSource)
                .load();
        patientModule.migrate();
        final var serviceModule = Flyway.configure()
                .schemas("services")
                .createSchemas(true)
                .locations("db/migration/service")
                .dataSource(dataSource)
                .load();
        serviceModule.migrate();
        final var hospitalModule = Flyway.configure()
                .schemas("hospitals")
                .createSchemas(true)
                .locations("db/migration/hospital")
                .dataSource(dataSource)
                .load();
        hospitalModule.migrate();
        final var doctorModule = Flyway.configure()
                .schemas("doctors")
                .createSchemas(true)
                .locations("db/migration/doctor")
                .dataSource(dataSource)
                .load();
        doctorModule.migrate();
        final var eventModule = Flyway.configure()
                .createSchemas(true)
                .locations("db/migration/event")
                .dataSource(dataSource)
                .load();
        eventModule.migrate();
    }
}
