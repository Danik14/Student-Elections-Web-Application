package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = HelperMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    User toDomain(UserEntity entity);

    List<User> toDomain(List<UserEntity> entities);

    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "candidatures", ignore = true)
    UserEntity toEntity (User user);

    UserResponse toResponse(User user);

    List<UserResponse> toResponse(List<User> users);
}
