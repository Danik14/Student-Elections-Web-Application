package gigachads.noenemies.diploma.api.controller.electionInProgress;

import gigachads.noenemies.diploma.TestHelper;
import gigachads.noenemies.diploma.api.dto.*;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import gigachads.noenemies.diploma.domain.model.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class ElectionControllerIT {
    private static final String BASE_RELATIVE_PATH = "/api/v1/election";

    @Autowired
    private TestHelper testHelper;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
    }

    private Map<String, String> singleParam(String key, String value) {
        Map<String, String> params = new HashMap<>();
        params.put(key, value);
        return params;
    }

    @Test
    void test_findElectionById_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/{electionId}", "9f5eb7fe-5531-4d59-b644-3b93c9abd8d1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().as(ElectionResponse.class);

        var expected = ElectionResponse.builder()
                .id(ElectionId.of("9f5eb7fe-5531-4d59-b644-3b93c9abd8d1"))
                .description("Election 2024")
                .status(ElectionStatus.IN_PROGRESS)
                .year(2024)
                .createdAt(LocalDateTime.parse("2024-01-20T14:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .deadline(LocalDateTime.parse("2024-03-31T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .stages(List.of(
                        StageResponse.builder()
                                .id(StageId.of("be316cf6-e4b7-415e-b21d-37fcf32815ab"))
                                .description("2024 stage 1")
                                .status(StageStatus.COMPLETED)
                                .votable(true)
                                .number(1)
                                .deadline(LocalDateTime.parse("2024-01-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                .build(),
                        StageResponse.builder()
                                .id(StageId.of("9dbc17ae-c522-48a7-b9f9-531d8b8a528f"))
                                .description("2024 stage 2")
                                .status(StageStatus.COMPLETED)
                                .votable(true)
                                .number(2)
                                .deadline(LocalDateTime.parse("2024-02-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                .build(),
                        StageResponse.builder()
                                .id(StageId.of("de9d9185-6d2e-4613-af3b-4d1733d0d9ea"))
                                .description("2024 stage 3")
                                .status(StageStatus.IN_PROGRESS)
                                .votable(true)
                                .number(3)
                                .deadline(LocalDateTime.parse("2024-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                .build()
                ))
                .build();
        assertEquals(expected, actual);
    }


    @Test
    void test_getElections_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath().getList(".", ElectionResponse.class);

        var expected = List.of(ElectionResponse.builder()
                        .id(ElectionId.of("9f5eb7fe-5531-4d59-b644-3b93c9abd8d1"))
                        .description("Election 2024")
                        .status(ElectionStatus.IN_PROGRESS)
                        .year(2024)
                        .createdAt(LocalDateTime.parse("2024-01-20T14:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                        .deadline(LocalDateTime.parse("2024-03-31T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                        .stages(List.of(
                                StageResponse.builder()
                                        .id(StageId.of("be316cf6-e4b7-415e-b21d-37fcf32815ab"))
                                        .description("2024 stage 1")
                                        .status(StageStatus.COMPLETED)
                                        .votable(true)
                                        .number(1)
                                        .deadline(LocalDateTime.parse("2024-01-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build(),
                                StageResponse.builder()
                                        .id(StageId.of("9dbc17ae-c522-48a7-b9f9-531d8b8a528f"))
                                        .description("2024 stage 2")
                                        .status(StageStatus.COMPLETED)
                                        .votable(true)
                                        .number(2)
                                        .deadline(LocalDateTime.parse("2024-02-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build(),
                                StageResponse.builder()
                                        .id(StageId.of("de9d9185-6d2e-4613-af3b-4d1733d0d9ea"))
                                        .description("2024 stage 3")
                                        .status(StageStatus.IN_PROGRESS)
                                        .votable(true)
                                        .number(3)
                                        .deadline(LocalDateTime.parse("2024-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build()
                        )).build(),
                ElectionResponse.builder()
                        .id(ElectionId.of("b09903bf-03fb-4c44-a925-29db853b9477"))
                        .description("Election 2023")
                        .status(ElectionStatus.COMPLETED)
                        .year(2023)
                        .createdAt(LocalDateTime.parse("2023-01-20T14:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                        .deadline(LocalDateTime.parse("2023-03-31T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                        .stages(List.of(
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
                        ))
                        .build());
        assertEquals(expected, actual);
    }

    @Test
    void test_getCurrentElection_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/current")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().as(ElectionResponse.class);

        var expected = ElectionResponse.builder()
                .id(ElectionId.of("9f5eb7fe-5531-4d59-b644-3b93c9abd8d1"))
                .description("Election 2024")
                .status(ElectionStatus.IN_PROGRESS)
                .year(2024)
                .createdAt(LocalDateTime.parse("2024-01-20T14:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .deadline(LocalDateTime.parse("2024-03-31T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                .stages(List.of(
                        StageResponse.builder()
                                .id(StageId.of("be316cf6-e4b7-415e-b21d-37fcf32815ab"))
                                .description("2024 stage 1")
                                .status(StageStatus.COMPLETED)
                                .votable(true)
                                .number(1)
                                .deadline(LocalDateTime.parse("2024-01-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                .build(),
                        StageResponse.builder()
                                .id(StageId.of("9dbc17ae-c522-48a7-b9f9-531d8b8a528f"))
                                .description("2024 stage 2")
                                .status(StageStatus.COMPLETED)
                                .votable(true)
                                .number(2)
                                .deadline(LocalDateTime.parse("2024-02-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                .build(),
                        StageResponse.builder()
                                .id(StageId.of("de9d9185-6d2e-4613-af3b-4d1733d0d9ea"))
                                .description("2024 stage 3")
                                .status(StageStatus.IN_PROGRESS)
                                .votable(true)
                                .number(3)
                                .deadline(LocalDateTime.parse("2024-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                .build()
                ))
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void test_createNewElection_success() {
        var requestBody = ElectionCreate.builder()
                .deadline(testHelper.toDefaultTime("2024-03-31T23:59:59"))
                .description("some description")
                .build();
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_RELATIVE_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().as(ElectionResponse.class);

        assertNotNull(actual.getId());
        var expected = ElectionResponse.builder()
                .id(actual.getId())
                .description("some description")
                .status(ElectionStatus.CREATED)
                .year(2024)
                .createdAt(actual.getCreatedAt())
                .deadline(testHelper.toDefaultTime("2024-03-31T23:59:59"))
                .stages(emptyList())
                .build();
        assertEquals(expected, actual);
    }
}