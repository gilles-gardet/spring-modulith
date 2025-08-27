# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Modulith sample application demonstrating modular monolith architecture with Java 21 and Maven. The application showcases module organization, event-driven communication, and automatically generated documentation in the medical/hospital domain.

## Architecture

The application follows Spring Modulith patterns with three main business modules:

- `service` - Manages medical services and consultations (depends on hospital)
- `hopital` - Manages hospitals, doctors, and treatments
- `patient` - Manages patients, medical records, and medications (listens to hospital events)
- `core` - Shared utilities, configuration, and exception handling

Each module follows hexagonal architecture:
- `internal/domain` - Business logic services
- `internal/entity` - JPA entities
- `internal/repository` - Data access layer
- `spi` - Service Provider Interface (DTOs, exceptions, mappers, external services)
- `package-info.java` - Module configuration with `@ApplicationModule`

Inter-module communication uses:
- Service interfaces for synchronous calls
- Spring Application Events for asynchronous communication
- Named interfaces pattern for loose coupling

## Development Commands

### Build and Run
```bash
./mvnw clean compile                    # Build the project
./mvnw spring-boot:run                  # Run the application
./mvnw clean package                    # Build JAR file
```

### Testing
```bash
./mvnw test                             # Run all tests
./mvnw test -Dtest=ModularityTests      # Run modularity compliance tests
./mvnw test -Dtest=HospitalIntegrationTests  # Run specific integration tests
```

### Database
The application uses PostgreSQL via Docker Compose. Docker containers start automatically via `spring-boot-docker-compose` dependency.

### Documentation Generation
Spring Modulith automatically generates documentation:
- Run `ModularityTests.writeDocumentationSnippets()` to generate PlantUML diagrams and module canvas files in `doc/` directory
- Module documentation is generated as `.adoc` and `.puml` files

## Key Technologies

- Spring Boot 3.3.1 with Java 21
- Spring Modulith 1.2.1 for modular architecture
- PostgreSQL with Flyway migrations (per-module in `src/main/resources/db/migration/`)
- TestContainers for integration testing
- MapStruct for mapping between entities and DTOs
- Lombok for boilerplate reduction

## Testing Strategy

- `ModularityTests` - Verifies module boundaries and generates documentation
- Integration tests per module using TestContainers
- REST Assured for API testing
- Awaitility for async event testing