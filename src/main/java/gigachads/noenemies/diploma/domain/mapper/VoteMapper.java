package gigachads.noenemies.diploma.domain.mapper;


import gigachads.noenemies.diploma.api.dto.VoteResponse;
import gigachads.noenemies.diploma.domain.model.Vote;
import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {HelperMapper.class, UserMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VoteMapper {
    Vote toDomain(VoteEntity entity);

    VoteResponse toResponse(Vote vote);

    VoteEntity toEntity(Vote vote);
}
