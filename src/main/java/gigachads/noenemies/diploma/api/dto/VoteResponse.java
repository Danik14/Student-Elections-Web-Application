package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.VoteId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Builder
@Getter
public class VoteResponse {
    @NonNull
    private VoteId id;

    @ToString.Exclude
    @NonNull
    private UserResponse elector;

    @ToString.Exclude
    @JsonIgnore
    @NonNull
    private CandidatureStageResponse candidatureStage;
}
