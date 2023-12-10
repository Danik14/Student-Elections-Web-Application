package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.domain.model.candidature.CandidatureId;
import gigachads.noenemies.diploma.domain.model.candidatureStage.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.election.ElectionId;
import gigachads.noenemies.diploma.domain.model.stage.StageId;
import gigachads.noenemies.diploma.domain.model.vote.VoteId;
import org.mapstruct.Mapper;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface HelperMapper {
    UUID map(ElectionId value);

    UUID map(StageId value);

    UUID map(CandidatureStageId value);

    UUID map(CandidatureId value);

    UUID map(VoteId value);
}
