package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
public class Candidature {
    @NonNull
    private final CandidatureId id;
    @NonNull
    private Election election;

    @ToString.Exclude
    @NonNull
    private User user;

    @ToString.Exclude
    @NonNull
    private List<CandidatureStage> candidatureStages;
}
