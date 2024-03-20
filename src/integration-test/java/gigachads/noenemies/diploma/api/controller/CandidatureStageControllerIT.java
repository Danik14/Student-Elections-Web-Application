package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.TestHelper;
import gigachads.noenemies.diploma.api.dto.*;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import gigachads.noenemies.diploma.domain.model.StageStatus;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;
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

@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class CandidatureStageControllerIT {
    private static final String BASE_RELATIVE_PATH = "/api/v1";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestHelper testHelper;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
    }

    @Test
    void test_getCandidatureStageById_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/candidature-stage/1efe9085-27b5-4b1c-9997-6c0144aaa8fa")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(CandidatureStageResponse.class);

        var expected = CandidatureStageResponse.builder()
                .id("1efe9085-27b5-4b1c-9997-6c0144aaa8fa")
                .candidature(
                        CandidatureResponse.builder()
                                .id("65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7")
                                .approvedById(UserId.of("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc"))
                                .user(
                                        UserResponse.builder()
                                                .id("6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be")
                                                .barcode("123456")
                                                .email("user1@example.com")
                                                .firstName("Candidate1")
                                                .lastName("1")
                                                .role(UserRole.valueOf("ACTIVE_CANDIDATE"))
                                                .build()
                                )
                                .build()
                )
                .votesCount(3)
                .stagePlan(
                        CandidatureStageInfoResponse.builder()
                                .description("some description user1 stage3")
                                .link1("http://localhost:8000/123")
                                .link2("http://localhost:8000/123")
                                .build()
                )
                .stage(
                        StageResponse.builder()
                                .id("de9d9185-6d2e-4613-af3b-4d1733d0d9ea")
                                .description("2024 stage 3")
                                .status(StageStatus.valueOf("IN_PROGRESS"))
                                .votable(true)
                                .number(3)
                                .deadline(testHelper.toDefaultTime("2024-03-20T23:59:59"))
                                .build()
                )
                .build();
        assertEquals(expected, actual);
    }

    @Test
    void test_getCandidatureStagesByElectionId_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/election/{electionId}/candidature-stage", "b09903bf-03fb-4c44-a925-29db853b9477")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath().getList(".", CandidatureStageResponse.class);

        var expected = List.of(
                CandidatureStageResponse.builder()
                        .id("646aa141-5b08-4ae7-a6e2-c7e02b2c9d0d")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("812991c5-646b-4fc7-bff9-f809cdeca5ef")
                                        .approvedById(UserId.of("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc"))
                                        .user(
                                                UserResponse.builder()
                                                        .id("30a567a9-37bb-4fc0-8659-ba7543403c6d")
                                                        .barcode("111111")
                                                        .email("gigabyte@example.com")
                                                        .firstName("gigabyte")
                                                        .lastName("jinsovich")
                                                        .role(UserRole.valueOf("ACTIVE_STUDENT"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .votesCount(2)
                        .stagePlan(
                                CandidatureStageInfoResponse.builder()
                                        .description("some description gigabyte stage1")
                                        .link1("http://localhost:8000/123")
                                        .link2("http://localhost:8000/123")
                                        .build()
                        )
                        .stage(
                                StageResponse.builder()
                                        .id("0552f1d3-e597-4e51-afcc-4b5d0fa94454")
                                        .description("2023 stage 1")
                                        .status(StageStatus.valueOf("COMPLETED"))
                                        .votable(true)
                                        .number(1)
                                        .deadline(LocalDateTime.parse("2023-02-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build()
                        )
                        .build(),

                CandidatureStageResponse.builder()
                        .id("52538b9b-d7a8-4b1e-8135-81aba2be894e")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("812991c5-646b-4fc7-bff9-f809cdeca5ef")
                                        .approvedById(UserId.of("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc"))
                                        .user(
                                                UserResponse.builder()
                                                        .id("30a567a9-37bb-4fc0-8659-ba7543403c6d")
                                                        .barcode("111111")
                                                        .email("gigabyte@example.com")
                                                        .firstName("gigabyte")
                                                        .lastName("jinsovich")
                                                        .role(UserRole.valueOf("ACTIVE_STUDENT"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .votesCount(2)
                        .stagePlan(
                                CandidatureStageInfoResponse.builder()
                                        .description("some description gigabyte stage2")
                                        .link1("http://localhost:8000/123")
                                        .link2("http://localhost:8000/123")
                                        .build()
                        )
                        .stage(
                                StageResponse.builder()
                                        .id("04cdaca4-a321-4968-93c2-068b7f3c9894")
                                        .description("2023 stage 2")
                                        .status(StageStatus.valueOf("COMPLETED"))
                                        .votable(true)
                                        .number(2)
                                        .deadline(LocalDateTime.parse("2023-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build()
                        )
                        .build(),

                CandidatureStageResponse.builder()
                        .id("70192ac1-7e41-4386-bb46-2f7f15d9e933")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("c858dc55-ba61-4fb8-ae5d-8d03e589be54")
                                        .approvedById(UserId.of("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc"))
                                        .user(
                                                UserResponse.builder()
                                                        .id("9a9c8cea-b3ce-484c-bbf5-01da56eaa632")
                                                        .barcode("222222")
                                                        .email("kamchick@example.com")
                                                        .firstName("kamchick")
                                                        .lastName("kamchickovich")
                                                        .role(UserRole.valueOf("ACTIVE_STUDENT"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .votesCount(3)
                        .stagePlan(
                                CandidatureStageInfoResponse.builder()
                                        .description("some description kamchik stage1")
                                        .link1("http://localhost:8000/123")
                                        .link2("http://localhost:8000/123")
                                        .build()
                        )
                        .stage(
                                StageResponse.builder()
                                        .id("0552f1d3-e597-4e51-afcc-4b5d0fa94454")
                                        .description("2023 stage 1")
                                        .status(StageStatus.valueOf("COMPLETED"))
                                        .votable(true)
                                        .number(1)
                                        .deadline(LocalDateTime.parse("2023-02-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build()
                        )
                        .build(),
                CandidatureStageResponse.builder()
                        .id("60576319-9fca-4179-bfd8-94bb9e59fa03")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("c858dc55-ba61-4fb8-ae5d-8d03e589be54")
                                        .approvedById(UserId.of("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc"))
                                        .user(
                                                UserResponse.builder()
                                                        .id("9a9c8cea-b3ce-484c-bbf5-01da56eaa632")
                                                        .barcode("222222")
                                                        .email("kamchick@example.com")
                                                        .firstName("kamchick")
                                                        .lastName("kamchickovich")
                                                        .role(UserRole.valueOf("ACTIVE_STUDENT"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .votesCount(3)
                        .stagePlan(
                                CandidatureStageInfoResponse.builder()
                                        .description("some description kamchik stage2")
                                        .link1("http://localhost:8000/123")
                                        .link2("http://localhost:8000/123")
                                        .build()
                        )
                        .stage(
                                StageResponse.builder()
                                        .id("04cdaca4-a321-4968-93c2-068b7f3c9894")
                                        .description("2023 stage 2")
                                        .status(StageStatus.valueOf("COMPLETED"))
                                        .votable(true)
                                        .number(2)
                                        .deadline(LocalDateTime.parse("2023-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build()
                        )
                        .build()
        );
        assertEquals(expected, actual);
    }

    @Test
    void test_getCurrentElectionCurrentCandidatureStages_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/election/current/candidature-stage/current")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath().getList(".", CandidatureStageResponse.class);

        var expected = List.of(
                CandidatureStageResponse.builder()
                        .id("1efe9085-27b5-4b1c-9997-6c0144aaa8fa")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7")
                                        .approvedById(UserId.of("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc"))
                                        .user(
                                                UserResponse.builder()
                                                        .id("6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be")
                                                        .barcode("123456")
                                                        .email("user1@example.com")
                                                        .firstName("Candidate1")
                                                        .lastName("1")
                                                        .role(UserRole.valueOf("ACTIVE_CANDIDATE"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .votesCount(3)
                        .stagePlan(
                                CandidatureStageInfoResponse.builder()
                                        .description("some description user1 stage3")
                                        .link1("http://localhost:8000/123")
                                        .link2("http://localhost:8000/123")
                                        .build()
                        )
                        .stage(
                                StageResponse.builder()
                                        .id("de9d9185-6d2e-4613-af3b-4d1733d0d9ea")
                                        .description("2024 stage 3")
                                        .status(StageStatus.valueOf("IN_PROGRESS"))
                                        .votable(true)
                                        .number(3)
                                        .deadline(LocalDateTime.parse("2024-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build()
                        )
                        .build(),
                CandidatureStageResponse.builder()
                        .id("01d2835e-ff45-4847-9a82-61bf83634b28")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("8dcf75a5-0636-425a-9ffd-b732d12ff197")
                                        .approvedById(UserId.of("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc"))
                                        .user(
                                                UserResponse.builder()
                                                        .id("ed28793a-14a3-48cb-a3d7-24ac1804bea8")
                                                        .barcode("654321")
                                                        .email("user2@example.com")
                                                        .firstName("Candidate2")
                                                        .lastName("2")
                                                        .role(UserRole.valueOf("ACTIVE_CANDIDATE"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .votesCount(2)
                        .stagePlan(
                                CandidatureStageInfoResponse.builder()
                                        .description("some description user2 stage3")
                                        .link1("http://localhost:8000/123")
                                        .link2("http://localhost:8000/123")
                                        .build()
                        )
                        .stage(
                                StageResponse.builder()
                                        .id("de9d9185-6d2e-4613-af3b-4d1733d0d9ea")
                                        .description("2024 stage 3")
                                        .status(StageStatus.valueOf("IN_PROGRESS"))
                                        .votable(true)
                                        .number(3)
                                        .deadline(LocalDateTime.parse("2024-03-20T23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                                        .build()
                        )
                        .build()
        );
        assertEquals(expected, actual);
    }

//    70192ac1-7e41-4386-bb46-2f7f15d9e933

    @Test
    @Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void test_getCandidatureStageInfoByCandidatureStageId_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/candidature-stage/{candidatureStageId}/info", "70192ac1-7e41-4386-bb46-2f7f15d9e933")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(CandidatureStageInfoResponse.class);

        var expected = CandidatureStageInfoResponse.builder()
                .description("some description kamchik stage1")
                .link1("http://localhost:8000/123")
                .link2("http://localhost:8000/123")
                .build();
        assertEquals(expected, actual);
    }
}
