package gigachads.noenemies.diploma.domain.mapper;


import gigachads.noenemies.diploma.api.dto.VoteResponse;
import gigachads.noenemies.diploma.domain.model.Vote;
import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {HelperMapper.class, UserMapper.class, CandidatureMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VoteMapper {
    Vote toDomain(VoteEntity entity);

    List<Vote> toDomain(List<VoteEntity> entities);

    VoteResponse toResponse(Vote vote);

    List<VoteResponse> toResponse(List<Vote> votes);

    VoteEntity toEntity(Vote vote);

//    @Mapping(target = "votesCount", expression = "java(entity.getVotes().size())")
//    CandidatureStage toCandidatureStageDomain(CandidatureStageEntity entity);
}
