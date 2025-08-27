package com.ggardet.classroom;

import com.ggardet.TestContainersConfiguration;
import com.ggardet.classroom.internal.repository.ClassroomRepository;
import com.ggardet.classroom.spi.dto.ClassroomDTO;
import com.ggardet.core.configuration.MultiModuleFlywayMigrationStrategy;
import com.ggardet.establishment.EstablishmentServiceInterface;
import com.ggardet.establishment.internal.entity.Establishment;
import com.ggardet.establishment.spi.event.EstablishmentDeletedEvent;
import com.google.gson.Gson;
import static io.restassured.RestAssured.given;
import lombok.RequiredArgsConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.modulith.events.core.EventPublicationRepository;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.UUID;

@ApplicationModuleTest(extraIncludes = "core", webEnvironment = RANDOM_PORT)
@Import({TestContainersConfiguration.class, MultiModuleFlywayMigrationStrategy.class})
@RequiredArgsConstructor
class ClassroomIntegrationTests {
    private static final UUID establishmentId = UUID.fromString("b8c115bd-57e7-4b15-b4ab-c05076179171");
    private final ClassroomRepository classroomRepository;
    private final EventPublicationRepository jpaEventPublicationRepository;
    @LocalServerPort
    public Integer serverPort;
    @MockBean
    EstablishmentServiceInterface establishmentServiceInterface;

    @Test
    void testEstablishmentCreationAndDeletion(Scenario scenario) {
        Mockito.when(establishmentServiceInterface.findEstablishmentById(establishmentId))
                .thenReturn(new Establishment());
        Mockito.when(establishmentServiceInterface.getMaxNumberOfClassroomByEstablishmentId(establishmentId))
                .thenReturn(1);
        final var gson = new Gson();
        final var classroomDTO = ClassroomDTO.builder()
                .name("Classe X")
                .establishmentId(establishmentId)
                .level("6ème")
                .maxStudentsCapacity(35)
                .build();
        final var classroomCreated = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .body(gson.toJson(classroomDTO))
                .when()
                .post("/classrooms")
                .then()
                .statusCode(CREATED.value())
                .extract()
                .as(ClassroomDTO.class);
        final var classroomFound = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/classrooms/" + classroomCreated.id())
                .then()
                .statusCode(OK.value())
                .extract()
                .as(ClassroomDTO.class);
        assertThat(classroomCreated).isEqualTo(classroomFound).isNotNull();
        assertThat(classroomCreated.id()).isEqualTo(classroomFound.id()).isNotNull();
        assertThat(classroomCreated.name()).isEqualTo(classroomFound.name()).isEqualTo("Classe X");
        assertThat(classroomCreated.establishmentId()).isEqualTo(classroomFound.establishmentId()).isNotNull();
        assertThat(classroomCreated.level()).isEqualTo(classroomFound.level()).isNotNull();
        assertThat(classroomCreated.maxStudentsCapacity()).isEqualTo(classroomFound.maxStudentsCapacity()).isEqualTo(35);
        scenario.publish(new EstablishmentDeletedEvent(establishmentId))
                .andWaitForStateChange(() -> classroomRepository.findByEstablishmentId(establishmentId))
                .andVerify(result -> {
                    assert result.isEmpty();
                });
        given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/classrooms/" + classroomCreated.id())
                .then()
                .statusCode(NOT_FOUND.value());
        await().until(() -> jpaEventPublicationRepository.findIncompletePublications().isEmpty());
    }
}
