package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

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
    @ToString.Exclude
    @NonNull
    private List<Vote> votes;
    @NonNull
    private Stage stage;
}
