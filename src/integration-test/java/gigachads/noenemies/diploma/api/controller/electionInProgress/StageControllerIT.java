package gigachads.noenemies.diploma.api.controller.electionInProgress;

import gigachads.noenemies.diploma.TestHelper;
import gigachads.noenemies.diploma.api.dto.StageCreate;
import gigachads.noenemies.diploma.api.dto.StageResponse;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.domain.model.StageId;
import gigachads.noenemies.diploma.domain.model.StageStatus;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class StageControllerIT {
    private static final String BASE_RELATIVE_PATH = "/api/v1/election";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestHelper testHelper;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
    }

    @Test
    public void test_getStagesByElectionId_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/{electionId}/stage", "b09903bf-03fb-4c44-a925-29db853b9477")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getList(".", StageResponse.class);

        var expected = List.of(
                StageResponse.builder()
                        .id(StageId.of("0552f1d3-e597-4e51-afcc-4b5d0fa94454"))
                        .description("2023 stage 1")
                        .status(StageStatus.COMPLETED)
                        .votable(true)
                        .number(1)
                        .deadline(LocalDateTime.parse("2023-02-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                        .build(),
                StageResponse.builder()
                        .id(StageId.of("04cdaca4-a321-4968-93c2-068b7f3c9894"))
                        .description("2023 stage 2")
                        .status(StageStatus.COMPLETED)
                        .votable(true)
                        .number(2)
                        .deadline(LocalDateTime.parse("2023-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                        .build()
        );
        assertEquals(expected, actual);
    }

    @Test
    public void test_getCurrentElectionStage_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/{electionId}/stage/current", "9f5eb7fe-5531-4d59-b644-3b93c9abd8d1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(StageResponse.class);

        var expected = StageResponse.builder()
                .id(StageId.of("de9d9185-6d2e-4613-af3b-4d1733d0d9ea"))
                .description("2024 stage 3")
                .status(StageStatus.IN_PROGRESS)
                .votable(true)
                .number(3)
                .deadline(LocalDateTime.parse("2024-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void test_getCurrentElectionCurrentStage_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/current/stage/current")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(StageResponse.class);

        var expected = StageResponse.builder()
                .id(StageId.of("de9d9185-6d2e-4613-af3b-4d1733d0d9ea"))
                .description("2024 stage 3")
                .status(StageStatus.IN_PROGRESS)
                .votable(true)
                .number(3)
                .deadline(LocalDateTime.parse("2024-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void test_createElectionStage_success() {
        Election election = testHelper.createDefaultElection();
        var requestBody = StageCreate.builder()
                .description("Test Description")
                .votable(true)
                .deadline(testHelper.toDefaultTime("2025-03-20T23:59:59"))
                .build();

        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_RELATIVE_PATH + "/{electionId}/stage", election.getId().getAsString())
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .as(StageResponse.class);

        assertNotNull(actual.getId());
        var expected = StageResponse.builder()
                .id(actual.getId())
                .description("Test Description")
                .status(StageStatus.CREATED)
                .votable(true)
                .number(1)
                .deadline(testHelper.toDefaultTime("2025-03-20T23:59:59"))
                .build();
        assertEquals(expected, actual);
    }
}