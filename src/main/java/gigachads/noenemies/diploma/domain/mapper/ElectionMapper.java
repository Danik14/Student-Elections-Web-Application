package gigachads.noenemies.diploma.domain.mapper;


import gigachads.noenemies.diploma.api.dto.election.ElectionResponse;
import gigachads.noenemies.diploma.domain.model.election.Election;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = HelperMapper.class)
public interface ElectionMapper {
    Election toDomain(ElectionEntity entity);

    ElectionEntity toEntity (Election election);

    ElectionResponse toResponse (Election election);


}
