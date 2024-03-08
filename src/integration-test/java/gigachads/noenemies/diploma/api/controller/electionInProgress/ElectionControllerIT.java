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
    void test_getCandidatureStagesByElectionId_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/{electionId}/candidature-stage", "b09903bf-03fb-4c44-a925-29db853b9477")
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
                                        .plan(
                                                CandidaturePlanResponse.builder()
                                                        .description("Description 1")
                                                        .slogan("Slogan 2023 1")
                                                        .instagramLink("https://instagram.com/link1")
                                                        .telegramLink("https://t.me/link1")
                                                        .build()
                                        )
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
                                CandidatureStagePlanResponse.builder()
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
                                        .plan(
                                                CandidaturePlanResponse.builder()
                                                        .description("Description 1")
                                                        .slogan("Slogan 2023 1")
                                                        .instagramLink("https://instagram.com/link1")
                                                        .telegramLink("https://t.me/link1")
                                                        .build()
                                        )
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
                                CandidatureStagePlanResponse.builder()
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
                                        .plan(
                                                CandidaturePlanResponse.builder()
                                                        .description("Description 2")
                                                        .slogan("Slogan 2023 2")
                                                        .instagramLink("https://instagram.com/link2")
                                                        .telegramLink("https://t.me/link2")
                                                        .build()
                                        )
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
                                CandidatureStagePlanResponse.builder()
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
                                        .plan(
                                                CandidaturePlanResponse.builder()
                                                        .description("Description 2")
                                                        .slogan("Slogan 2023 2")
                                                        .instagramLink("https://instagram.com/link2")
                                                        .telegramLink("https://t.me/link2")
                                                        .build()
                                        )
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
                                CandidatureStagePlanResponse.builder()
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
                .get(BASE_RELATIVE_PATH + "/current/candidature-stage/current")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath().getList(".", CandidatureStageResponse.class);

        System.out.println(actual);

        var expected = List.of(
                CandidatureStageResponse.builder()
                        .id("1efe9085-27b5-4b1c-9997-6c0144aaa8fa")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7")
                                        .plan(
                                                CandidaturePlanResponse.builder()
                                                        .description("Description 1")
                                                        .slogan("Slogan 1")
                                                        .instagramLink("https://instagram.com/link1")
                                                        .telegramLink("https://t.me/link1")
                                                        .build()
                                        )
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
                                CandidatureStagePlanResponse.builder()
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
                                        .plan(
                                                CandidaturePlanResponse.builder()
                                                        .description("Description 2")
                                                        .slogan("Slogan 2")
                                                        .instagramLink("https://instagram.com/link2")
                                                        .telegramLink("https://t.me/link2")
                                                        .build()
                                        )
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
                                CandidatureStagePlanResponse.builder()
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

        System.out.println(actual);

        var expected = CandidatureStageResponse.builder()
                        .id("1efe9085-27b5-4b1c-9997-6c0144aaa8fa")
                        .candidature(
                                CandidatureResponse.builder()
                                        .id("65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7")
                                        .plan(
                                                CandidaturePlanResponse.builder()
                                                        .description("Description 1")
                                                        .slogan("Slogan 1")
                                                        .instagramLink("https://instagram.com/link1")
                                                        .telegramLink("https://t.me/link1")
                                                        .build()
                                        )
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
                                CandidatureStagePlanResponse.builder()
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