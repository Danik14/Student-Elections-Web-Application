package gigachads.noenemies.diploma.api.controller.electionInProgress;


import gigachads.noenemies.diploma.TestHelper;
import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.containers.ContainerHolder;
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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class UserControllerIT {
    private static final String BASE_RELATIVE_PATH = "/api/v1/user";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestHelper testHelper;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
    }

    @Test
    void test_getCurrentUser_success() {
        var actual = given()
                .auth().principal(testHelper.getTestOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/current")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().as(UserResponse.class);

        var expected = UserResponse.builder()
                .id(UserId.of("2004cc3b-a00b-649b-0000-000000000000"))
                .email("211360@astanait.edu.kz")
                .barcode("211360")
                .photo(new byte[]{})
                .role(UserRole.SUPER_ADMIN)
                .firstName("Daniyar")
                .lastName("Chapagan")
                .build();
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("photo")
                .isEqualTo(expected);
    }

    @Test
    void test_getUserById_success() {
        var actual = given()
                .auth().principal(testHelper.getTestOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/9a9c8cea-b3ce-484c-bbf5-01da56eaa632")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().as(UserResponse.class);

        var expected = UserResponse.builder()
                .id(UserId.of("9a9c8cea-b3ce-484c-bbf5-01da56eaa632"))
                .email("kamchick@example.com")
                .barcode("222222")
                .photo(new byte[]{})
                .role(UserRole.ACTIVE_STUDENT)
                .firstName("kamchick")
                .lastName("kamchickovich")
                .build();
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("photo")
                .isEqualTo(expected);
    }

    @Test
    void test_getStudentByBarcode_success() {
        var actual = given()
                .auth().principal(testHelper.getTestOauth2TokenPrincipal())
                .log().all()
                .header("Accept", "application/json")
                .when()
                .get(BASE_RELATIVE_PATH + "/barcode/" + "222222")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().as(UserResponse.class);

        var expected = UserResponse.builder()
                .id(UserId.of("9a9c8cea-b3ce-484c-bbf5-01da56eaa632"))
                .email("kamchick@example.com")
                .barcode("222222")
                .photo(new byte[]{})
                .role(UserRole.ACTIVE_STUDENT)
                .firstName("kamchick")
                .lastName("kamchickovich")
                .build();
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("photo")
                .isEqualTo(expected);
    }
}
