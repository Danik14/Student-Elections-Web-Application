package gigachads.noenemies.diploma.domain.model.candidatureStage;

import gigachads.noenemies.diploma.domain.model.candidature.Candidature;
import gigachads.noenemies.diploma.domain.model.stage.Stage;
import gigachads.noenemies.diploma.domain.model.vote.Vote;
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

    @ToString.Exclude
    @NonNull
    private Stage stage;

    @ToString.Exclude
    @NonNull
    private Candidature candidature;

    @ToString.Exclude
    @NonNull
    private List<Vote> votes;
}
