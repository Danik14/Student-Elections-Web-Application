package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.domain.model.user.User;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = HelperMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity (User user);
}
