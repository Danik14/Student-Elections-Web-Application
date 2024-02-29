package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gigachads.noenemies.diploma.domain.model.VoteId;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponse {
    @NonNull
    private VoteId id;

    @NonNull
    @JsonIgnoreProperties("photo")
    private UserResponse elector;

    @NonNull
    @JsonIgnoreProperties({"stagePlan", "stage"})
    private CandidatureStageResponse candidatureStage;
}
