package gigachads.noenemies.diploma.api.controller.electionInProgress;

import gigachads.noenemies.diploma.TestHelper;
import gigachads.noenemies.diploma.api.dto.CandidatureResponse;
import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import gigachads.noenemies.diploma.domain.model.UserRole;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class CandidatureControllerIT {
    private static final String BASE_RELATIVE_PATH = "/api/v1/candidature";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestHelper testHelper;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
    }

    @Test
    public void test_getActiveCandidatures_success() {
        var actual = given()
                .auth().principal(testHelper.getTestSuperAdminOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/active")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getList(".", CandidatureResponse.class);

        var expected = List.of(CandidatureResponse.builder()
                        .id("65e52afe-a8d5-4ab1-a576-3ad0fdb6e7c7")
                        .approvedById("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc")
                        .user(UserResponse.builder()
                                .id("6218ecf0-a1ae-43cb-b2bc-ec06dc83e5be")
                                .barcode("123456")
                                .email("user1@example.com")
                                .firstName("Candidate1")
                                .lastName("1")
                                .role(UserRole.ACTIVE_CANDIDATE)
                                .build())
                .build(),
                CandidatureResponse.builder()
                .id("8dcf75a5-0636-425a-9ffd-b732d12ff197")
                .approvedById("0e35ae6f-3ebb-4f3a-98b3-4c20b619cffc")
                .user(UserResponse.builder()
                        .id("ed28793a-14a3-48cb-a3d7-24ac1804bea8")
                        .barcode("654321")
                        .email("user2@example.com")
                        .firstName("Candidate2")
                        .lastName("2")
                        .role(UserRole.ACTIVE_CANDIDATE)
                        .build())
                .build());
        assertEquals(expected, actual);
    }


    @Test
    @Disabled
    @Sql(scripts = "classpath:test-scripts/test-created-election.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void test_applyForCandidature_success() {
        var actual = given()
                .auth().principal(testHelper.getTestActiveStudentOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .post(BASE_RELATIVE_PATH + "/apply")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(String.class);

        var expected = "";
        assertEquals(expected, actual);
    }
}