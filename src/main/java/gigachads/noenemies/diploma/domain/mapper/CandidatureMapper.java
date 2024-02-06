package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.api.dto.CandidatureResponse;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = HelperMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CandidatureMapper {
    Candidature toDomain(CandidatureEntity entity);

    List<Candidature> toDomain(List<CandidatureEntity> entities);

    CandidatureResponse toResponse(Candidature candidature);

    List<CandidatureResponse> toResponse(List<Candidature> candidature);
}
