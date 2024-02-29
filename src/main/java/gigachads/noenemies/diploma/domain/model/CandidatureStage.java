package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
public class CandidatureStage {
    @NonNull
    private CandidatureStageId id;
    @NonNull
    private CandidatureStagePlan stagePlan;
    @ToString.Exclude
    @NonNull
    private Candidature candidature;
    @NonNull
    private Integer votesCount;
    @NonNull
    private Stage stage;
}
