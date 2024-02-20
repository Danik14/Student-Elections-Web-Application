package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanResponse;
import gigachads.noenemies.diploma.api.dto.CandidatureResponse;
import gigachads.noenemies.diploma.api.dto.CandidatureStageResponse;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.Vote;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidaturePlanEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {HelperMapper.class, UserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CandidatureMapper {
    Candidature toDomain(CandidatureEntity entity);

    List<Candidature> toDomain(List<CandidatureEntity> entities);

    @Mapping(target = "user", source = "candidature.user")
    CandidatureResponse toResponse(Candidature candidature);

    List<CandidatureResponse> toResponse(List<Candidature> candidature);

    @Mapping(target = "votesCount", expression = "java(entity.getVotes().size())")
    CandidatureStage toCandidatureStageDomain(CandidatureStageEntity entity);

    List<CandidatureStage> toCandidatureStageDomain(List<CandidatureStageEntity> entities);

    CandidatureStageResponse toCandidatureStageResponse(CandidatureStage candidatureStage);

    List<CandidatureStageResponse> toCandidatureStageResponse(List<CandidatureStage> candidatureStages);

    CandidaturePlan toCandidaturePlanDomain(CandidaturePlanEntity candidaturePlanEntity);

    CandidaturePlanResponse toCandidaturePlanResponse(CandidaturePlan candidaturePlan);

//    Vote voteEntityToDomain (VoteEntity entity);

//    default List<Vote> voteEntityListToDomain(List<VoteEntity> voteEntities) {
//        if (voteEntities == null){
//            return new ArrayList<>();
//        }
//
//        return voteEntities.stream().map(this::voteEntityToDomain).toList();
//    }
}
