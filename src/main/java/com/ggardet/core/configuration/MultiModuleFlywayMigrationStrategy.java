package com.ggardet.core.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

@Component
public class MultiModuleFlywayMigrationStrategy implements FlywayMigrationStrategy {

    @Override
    public void migrate(final Flyway flyway) {
        final var dataSource = flyway.getConfiguration().getDataSource();
        final var studentModule = Flyway.configure()
                .schemas("students")
                .createSchemas(true)
                .locations("db/migration/student")
                .dataSource(dataSource)
                .load();
        studentModule.migrate();
        final var classroomModule = Flyway.configure()
                .schemas("classrooms")
                .createSchemas(true)
                .locations("db/migration/classroom")
                .dataSource(dataSource)
                .load();
        classroomModule.migrate();
        final var establishmentModule = Flyway.configure()
                .schemas("establishments")
                .createSchemas(true)
                .locations("db/migration/establishment")
                .dataSource(dataSource)
                .load();
        establishmentModule.migrate();
        final var eventModule = Flyway.configure()
                .createSchemas(true)
                .locations("db/migration/event")
                .dataSource(dataSource)
                .load();
        eventModule.migrate();
    }
}
