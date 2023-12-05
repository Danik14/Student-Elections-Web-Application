package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.domain.model.user.User;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity (User user);
}
