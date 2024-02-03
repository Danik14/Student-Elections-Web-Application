package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.Stage;
import gigachads.noenemies.diploma.domain.model.Vote;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
