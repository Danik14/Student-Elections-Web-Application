package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;


@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class UserControllerIT{
    private static final String BASE_RELATIVE_PATH = "/api/v1/user";

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private MockMvc mvc;

    private Principal mPrincipal;

    @BeforeEach
    void setUp() {
        HashMap<String, Object> userAttributes = new HashMap<>();
        userAttributes.put("sub", "gZJnr1mzmbe98A6nn1NCwrvy4UCnbrRnIyYZYc4xIyo");
        userAttributes.put("preferred_username", "211360@astanait.edu.kz");
        userAttributes.put("given_name", "Daniyar");
        userAttributes.put("family_name", "Chapagan");
        mPrincipal = new OAuth2AuthenticationToken(
                new DefaultOAuth2User(
                        Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                        userAttributes,
                        "sub"
                ),
                Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                "123"
        );
        mockMvc(mvc);

    }

    private Map<String, String> singleParam(String key, String value) {
        Map<String, String> params = new HashMap<>();
        params.put(key, value);
        return params;
    }

    @Test
    // TODO
    void test_getCurrentUser_success() {
        var actual = given()
                .auth().principal(mPrincipal)
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
//        assertThat(response.getBody())
//                .extracting(UserDTO::getEmail, UserDTO::getFirstName, UserDTO::getLastName)
//                .containsExactly("user2@some.com", "John", "Duke");
    }
}
