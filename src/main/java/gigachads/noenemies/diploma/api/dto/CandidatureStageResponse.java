package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
public class CandidatureStageResponse {
    @NonNull
    private CandidatureStageId id;

    @ToString.Exclude
    @NonNull
    private StageResponse stage;

    @ToString.Exclude
    @JsonIgnore
    @NonNull
    private CandidatureResponse candidature;

    @ToString.Exclude
    @JsonIgnore
    @NonNull
    private List<VoteResponse> votes;
}
