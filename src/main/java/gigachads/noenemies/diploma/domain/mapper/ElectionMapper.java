package gigachads.noenemies.diploma.domain.mapper;


import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = HelperMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ElectionMapper {
    Election toDomain(ElectionEntity entity);

    List<Election> toDomain(List<ElectionEntity> entities);

    ElectionEntity toEntity (Election election);

    ElectionEntity toEntity (ElectionCreate create);

    ElectionResponse toResponse (Election election);

    List<ElectionResponse> toResponse (List<Election> elections);
}
