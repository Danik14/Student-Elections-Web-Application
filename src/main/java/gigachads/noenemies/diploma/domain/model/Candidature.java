package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
public class Candidature {
    @NonNull
    private final CandidatureId id;
    @NonNull
    private CandidaturePlan plan;
    private boolean approved;

    @ToString.Exclude
    @NonNull
    private User approvedBy;
    @ToString.Exclude
    @NonNull
    private User user;
}
