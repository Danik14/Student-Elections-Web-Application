package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;


@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:test-scripts/test-election-in-progress.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@ExtendWith(ContainerHolder.class)
public class UserControllerIT{
    private static final String BASE_RELATIVE_PATH = "/api/v1/user";

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
    void test_getCurrentUser_success() {
        //OAuth2AuthenticationToken [Principal=Name: [gZJnr1mzmbe98A6nn1NCwrvy4UCnbrRnIyYZYc4xIyo], Granted Authorities: [[OIDC_USER, SCOPE_email, SCOPE_openid, SCOPE_profile]], User Attributes: [{sub=gZJnr1mzmbe98A6nn1NCwrvy4UCnbrRnIyYZYc4xIyo, ver=2.0, aio=ATQAy/8WAAAAwvahRDO8LQiFfT/Lig/6I6zJfO5gLABCVQ2IoBnSV3jBzl1+JR9Gs5SJ9OJEmPwA, iss=https://login.microsoftonline.com/158f15f3-83e0-4906-824c-69bdc50d9d61/v2.0, oid=29718428-14e1-4482-9b32-e4b806e58320, preferred_username=211360@astanait.edu.kz, uti=2gez1DUaq0KLGjOPfWZFAA, given_name=Daniyar, nonce=_ZsaVBbwqS0xUvruceQlRO0lEXXubRD6C4-eH4G6Hjg, picture=https://graph.microsoft.com/v1.0/me/photo/$value, tid=158f15f3-83e0-4906-824c-69bdc50d9d61, aud=[f66ef411-1b58-4aa5-9b11-5eebc9e4d715], nbf=Mon Feb 26 12:59:59 ALMT 2024, rh=0.ATwA8xWPFeCDBkmCTGm9xQ2dYRH0bvZYG6VKmxFe68nk1xU8AEU., name=Daniyar Chapagan, exp=2024-02-26T08:04:59Z, family_name=Chapagan, iat=2024-02-26T06:59:59Z, email=211360@astanait.edu.kz}], Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=127.0.0.1, SessionId=F3729A12C38CC92AC2A5C10FBF9BBB61], Granted Authorities=[OIDC_USER, SCOPE_email, SCOPE_openid, SCOPE_profile]]
        var actual = given()
                .auth().preemptive().basic("123", "123")
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
