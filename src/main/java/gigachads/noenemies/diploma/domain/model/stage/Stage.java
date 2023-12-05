package gigachads.noenemies.diploma.domain.model.stage;

import gigachads.noenemies.diploma.domain.model.candidatureStage.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.election.Election;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Stage {
    @NonNull
    private final StageId id;
    @NonNull
    private final String description;

    @NonNull
    private final LocalDateTime deadline;

    @ToString.Exclude
    @NonNull
    private final Election election;

    @ToString.Exclude
    @NonNull
    private final List<CandidatureStage> candidatureStages;
}
