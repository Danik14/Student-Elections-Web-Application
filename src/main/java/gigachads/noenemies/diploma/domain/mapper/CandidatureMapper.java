package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.api.dto.*;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.CandidatureStageInfo;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidaturePlanEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageInfoEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {HelperMapper.class, UserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CandidatureMapper {
    Candidature toDomain(CandidatureEntity entity);

    List<Candidature> toDomain(List<CandidatureEntity> entities);

    @Mapping(target = "approvedById", source = "approvedBy.id")
    @Mapping(target = "user", source = "candidature.user")
    CandidatureResponse toResponse(Candidature candidature);

    List<CandidatureResponse> toResponse(List<Candidature> candidature);

    @Mapping(target = "votesCount", expression = "java(entity.getVotes().size())")
    CandidatureStage toCandidatureStageDomain(CandidatureStageEntity entity);

    List<CandidatureStage> toCandidatureStageDomain(List<CandidatureStageEntity> entities);

    CandidatureStageResponse toCandidatureStageResponse(CandidatureStage candidatureStage);

    List<CandidatureStageResponse> toCandidatureStageResponse(List<CandidatureStage> candidatureStages);

    CandidaturePlan toCandidaturePlanDomain(CandidaturePlanEntity entity);

    CandidaturePlanResponse toCandidaturePlanResponse(CandidaturePlan candidaturePlan);

    CandidatureStageInfo toCandidatureStageInfoDomain(CandidatureStageInfoEntity entity);

    CandidatureStageInfoResponse toCandidatureStageInfoResponse(CandidatureStageInfo candidatureStageInfo);

    CandidatureStageInfoEntity toCandidatureStageInfoEntity(CandidatureStageInfoUpdate update);


//    Vote voteEntityToDomain (VoteEntity entity);

//    default List<Vote> voteEntityListToDomain(List<VoteEntity> voteEntities) {
//        if (voteEntities == null){
//            return new ArrayList<>();
//        }
//
//        return voteEntities.stream().map(this::voteEntityToDomain).toList();
//    }
}
