package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.domain.model.StageId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class StageResponse {
    @NonNull
    private final StageId id;
    @NonNull
    private final String description;

    @NonNull
    private final LocalDateTime deadline;

    @ToString.Exclude
    @JsonIgnore
    @NonNull
    private final ElectionResponse election;

    @ToString.Exclude
    @JsonIgnore
    @NonNull
    private final List<CandidatureStageResponse> candidatureStages;
}
