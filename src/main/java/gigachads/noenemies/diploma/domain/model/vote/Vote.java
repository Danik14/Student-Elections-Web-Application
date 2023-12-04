package gigachads.noenemies.diploma.domain.model.vote;

import gigachads.noenemies.diploma.domain.model.candidatureStage.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
public class Vote {
    @NonNull
    private VoteId id;

    @ToString.Exclude
    @NonNull
    private User elector;

    @ToString.Exclude
    @NonNull
    private CandidatureStage candidatureStage;
}
