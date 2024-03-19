package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureStageResponse {
    @NonNull
    private CandidatureStageId id;
    @NonNull
    private CandidatureResponse candidature;
    @NonNull
    private Integer votesCount;
    @NonNull
    private CandidatureStageInfoResponse stagePlan;
    @NonNull
    private StageResponse stage;

    public static class CandidatureStageResponseBuilder {
        public CandidatureStageResponseBuilder id(CandidatureStageId id){
            this.id = id;
            return this;
        }

        public CandidatureStageResponseBuilder id(String stringId){
            return id(CandidatureStageId.of(stringId));
        }
    }
}
