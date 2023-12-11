package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.domain.model.user.User;
import gigachads.noenemies.diploma.domain.model.user.UserRole;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private UserMapper service;

    @BeforeEach
    void setUp() {
        HelperMapper helperMapper = new HelperMapperImpl();
        service = new UserMapperImpl(helperMapper);
    }

    @Test
    void testToDomain() {
    }

    @Test
    void testToEntity() {
        var user = User.builder()
                .id("dd1213a7-8491-4c4b-8381-e28566ff317b")
                .firstName("Aliba")
                .lastName("Zesty")
                .email("alibazesty@test.com")
                .role(UserRole.STUDENT)
                .barcode("211360")
                .build();

        var actual = service.toEntity(user);

        var expected = UserEntity.builder()
                .id(UUID.fromString("dd1213a7-8491-4c4b-8381-e28566ff317b"))
                .barcode("211360")
                .firstName("Aliba")
                .lastName("Zesty")
                .email("alibazesty@test.com")
                .role(UserRole.STUDENT)
                .build();
        assertEquals(expected, actual);
    }
}