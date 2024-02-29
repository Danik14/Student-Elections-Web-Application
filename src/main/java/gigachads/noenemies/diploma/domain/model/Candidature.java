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

    @ToString.Exclude
    @NonNull
    private User approvedBy;
    @ToString.Exclude
    @NonNull
    private User user;

    public static class CandidatureBuilder {
        public CandidatureBuilder id(CandidatureId id){
            this.id = id;
            return this;
        }

        public CandidatureBuilder id(String stringId){
            return id(CandidatureId.of(stringId));
        }
    }
}
