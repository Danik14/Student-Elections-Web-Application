package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.VoteId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class VoteResponse {
    @NonNull
    private final VoteId id;

    @NonNull
    @JsonIgnoreProperties("photo")
    private final UserResponse elector;

    @NonNull
    @JsonIgnoreProperties({"stagePlan", "stage"})
    private final CandidatureStageResponse candidatureStage;
}
