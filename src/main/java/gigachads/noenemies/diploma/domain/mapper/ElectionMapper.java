package gigachads.noenemies.diploma.domain.mapper;


import gigachads.noenemies.diploma.api.dto.ElectionCreateRequest;
import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = HelperMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ElectionMapper {
    Election toDomain(ElectionEntity entity);

    List<Election> toDomain(List<ElectionEntity> entities);

    ElectionEntity toEntity (Election election);

    @Mapping(target = "year", ignore = true)
    @Mapping(target = "totalVotesCount", ignore = true, defaultValue = "0")
    @Mapping(target = "status", ignore = true, defaultValue = "true")
    @Mapping(target = "stages", ignore = true)
    @Mapping(target = "candidatures", ignore = true)
    @Mapping(target = "active", ignore = true, defaultValue = "true")
    ElectionEntity toEntity (ElectionCreateRequest create);

    ElectionResponse toResponse (Election election);

    List<ElectionResponse> toResponse (List<Election> elections);
}
