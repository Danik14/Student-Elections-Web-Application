package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.election.ElectionResponse;
import gigachads.noenemies.diploma.containers.ContainerHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "test_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestPropertySource(locations = "classpath:application-test.yml")
@ExtendWith(ContainerHolder.class)
public class ElectionControllerIT {

    @Autowired
    private TestRestTemplate template;

    private Map<String, String> singleParam(String key, String value) {
        Map<String, String> params = new HashMap<>();
        params.put(key, value);
        return params;
    }

    @Test
    @DisplayName("Testing to finding user details by id")
    void findUserByIdTest() {
        ResponseEntity<ElectionResponse> response = template.getForEntity("/api/v1/elections/{id}", ElectionResponse.class, singleParam("id", "813a19ae-49ce-4934-917c-78d2d7dc6153"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

//        assertThat(response.getBody())
//                .extracting(UserDTO::getEmail, UserDTO::getFirstName, UserDTO::getLastName)
//                .containsExactly("user2@some.com", "John", "Duke");
    }
}
