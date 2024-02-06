package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserCreate;
import gigachads.noenemies.diploma.domain.model.UserRole;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

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

    default UserEntity toEntity(UserCreate create) {
        String barcode = create.getEmail().substring(0, 6);
        boolean barcodeMatch = barcode.matches("\\d{6}");
        return UserEntity.builder()
                .email(create.getEmail())
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .role(barcodeMatch ? UserRole.ACTIVE_STUDENT : UserRole.USER)
                .barcode(barcodeMatch ? barcode : "")
                .build();
    }

    UserResponse toResponse(User user);

    List<UserResponse> toResponse(List<User> users);
}
