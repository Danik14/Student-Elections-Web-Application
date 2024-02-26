package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;


@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class ElectionControllerIT {
    private static final String BASE_RELATIVE_PATH = "/api/v1/election";

    @Autowired
    private TestRestTemplate template;

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
    @DisplayName("Testing to finding user details by id")
    @SneakyThrows
    void test_findElectionById_success() {
        var actual = given()
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
//        assertThat(response.getBody())
//                .extracting(UserDTO::getEmail, UserDTO::getFirstName, UserDTO::getLastName)
//                .containsExactly("user2@some.com", "John", "Duke");
    }
}



//.auth().principal(new OAuth2AuthenticationToken(
//        new DefaultOAuth2User(
//        Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
//        new HashMap<>(),
//        ""
//        ),
//        Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
//        ""
//        )
//        )