package gigachads.noenemies.diploma.domain.model.candidatureStage;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.StageEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Set;

@Getter
@Builder
public class CandidatureStage {
    @NonNull
    private CandidatureStageId id;

    @ToString.Exclude
    @NonNull
    private StageEntity stage;

    @ToString.Exclude
    @NonNull
    private CandidatureEntity candidature;

    @ToString.Exclude
    @NonNull
    private Set<VoteEntity> votes;
}
