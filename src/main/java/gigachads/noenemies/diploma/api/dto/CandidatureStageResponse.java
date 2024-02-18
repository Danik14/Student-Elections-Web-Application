package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class CandidatureStageResponse {
    @NonNull
    private CandidatureStageId id;
    @NonNull
    private CandidatureResponse candidature;
    @NonNull
    private List<VoteResponse> votes;
    @NonNull
    private CandidatureStagePlanResponse stagePlan;

    public int getVotesCount() {
        return votes.size();
    }
}
