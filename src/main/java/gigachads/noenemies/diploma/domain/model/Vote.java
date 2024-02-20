package gigachads.noenemies.diploma.domain.model;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class Vote {
    @NonNull
    private final VoteId id;

    @NonNull
    private final User elector;

    @NonNull
    private final CandidatureStage candidatureStage;
}
