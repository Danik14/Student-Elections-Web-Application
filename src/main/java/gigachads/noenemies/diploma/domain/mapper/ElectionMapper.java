package gigachads.noenemies.diploma.domain.mapper;


import gigachads.noenemies.diploma.api.dto.election.ElectionResponse;
import gigachads.noenemies.diploma.domain.model.election.Election;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = HelperMapper.class)
public interface ElectionMapper {
    Election toDomain(ElectionEntity entity);

    ElectionEntity toEntity (Election election);

    ElectionResponse toResponse (Election election);


}
