package com.ggardet.student;

import com.google.gson.Gson;
import com.ggardet.TestContainersConfiguration;
import com.ggardet.classroom.ClassroomServiceInterface;
import com.ggardet.classroom.internal.entity.Classroom;
import com.ggardet.core.configuration.MultiModuleFlywayMigrationStrategy;
import com.ggardet.establishment.EstablishmentServiceInterface;
import com.ggardet.establishment.internal.entity.Establishment;
import com.ggardet.establishment.spi.event.EstablishmentDeletedEvent;
import com.ggardet.student.internal.repository.StudentRepository;
import com.ggardet.student.spi.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ApplicationModuleTest(extraIncludes = "core", webEnvironment = RANDOM_PORT)
@Import({TestContainersConfiguration.class, MultiModuleFlywayMigrationStrategy.class})
@RequiredArgsConstructor
class StudentIntegrationTests {
    private static final UUID establishmentId = UUID.fromString("b8c115bd-57e7-4b15-b4ab-c05076179171");
    private static final UUID classroomId = UUID.fromString("c8c115bd-57e7-4b15-b4ab-c05076179171");
    private final StudentRepository studentRepository;
    @LocalServerPort
    public Integer serverPort;
    @MockBean
    EstablishmentServiceInterface establishmentServiceInterface;
    @MockBean
    ClassroomServiceInterface classroomServiceInterface;

    @Test
    void testStudentCreation(Scenario scenario) {
        Mockito.when(establishmentServiceInterface.findEstablishmentById(establishmentId))
                .thenReturn(new Establishment());
        Mockito.when(classroomServiceInterface.findClassroomById(classroomId))
                .thenReturn(new Classroom());
        final var gson = new Gson();
        final var studentDTO = StudentDTO.builder()
                .classroomId(classroomId)
                .establishmentId(establishmentId)
                .firstname("John")
                .lastname("Doe")
                .birthdate("2000-01-01")
                .build();
        final var studentCreated = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .body(gson.toJson(studentDTO))
                .when()
                .post("/students")
                .then()
                .statusCode(CREATED.value())
                .extract()
                .as(StudentDTO.class);
        final var studentFound = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/students/" + studentCreated.id())
                .then()
                .statusCode(OK.value())
                .extract()
                .as(StudentDTO.class);
        assertThat(studentCreated).isEqualTo(studentFound).isNotNull();
        assertThat(studentCreated.id()).isEqualTo(studentFound.id()).isNotNull();
        assertThat(studentCreated.firstname()).isEqualTo(studentFound.firstname()).isEqualTo("John");
        assertThat(studentCreated.lastname()).isEqualTo(studentFound.lastname()).isEqualTo("Doe");
        assertThat(studentCreated.birthdate()).isEqualTo(studentFound.birthdate()).isNotNull();
        assertThat(studentCreated.classroomId()).isEqualTo(studentFound.classroomId()).isEqualTo(classroomId);
        assertThat(studentCreated.establishmentId()).isEqualTo(studentFound.establishmentId()).isEqualTo(establishmentId);
        scenario.publish(new EstablishmentDeletedEvent(establishmentId))
                .andWaitForStateChange(() -> studentRepository.findAllByEstablishmentId(establishmentId),
                        List::isEmpty);
        final var finalStudentDTO = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/students/" + studentCreated.id())
                .then()
                .statusCode(OK.value())
                .extract()
                .as(StudentDTO.class);
        assertThat(finalStudentDTO.classroomId()).isNotNull();
        assertThat(finalStudentDTO.establishmentId()).isNull();
    }
}
