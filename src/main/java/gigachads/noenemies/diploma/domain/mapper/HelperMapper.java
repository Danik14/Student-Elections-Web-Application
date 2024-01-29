package gigachads.noenemies.diploma.domain.mapper;

import gigachads.noenemies.diploma.domain.model.CandidatureId;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.StageId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.VoteId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HelperMapper {
    default UUID map(ElectionId value) {
        return value.getId();
    }

    default UUID map(StageId value){
        return value.getId();
    }

    default UUID map(CandidatureStageId value){
        return value.getId();
    }

    default UUID map(CandidatureId value){
        return value.getId();
    }

    default UUID map(VoteId value){
        return value.getId();
    }

    default UUID map(UserId value){
        return value.getId();
    }
}
