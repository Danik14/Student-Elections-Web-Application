package gigachads.noenemies.diploma.domain.mapper;


import gigachads.noenemies.diploma.api.dto.StageCreate;
import gigachads.noenemies.diploma.api.dto.StageResponse;
import gigachads.noenemies.diploma.domain.model.Stage;
import gigachads.noenemies.diploma.storage.jpa.entity.StageEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = HelperMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StageMapper {

    Stage toDomain(StageEntity entity);

    List<Stage> toDomain(List<StageEntity> entity);

    StageResponse toResponse(Stage stage);

    List<StageResponse> toResponse(List<Stage> stages);

    StageEntity toEntity(StageCreate stage);
}
