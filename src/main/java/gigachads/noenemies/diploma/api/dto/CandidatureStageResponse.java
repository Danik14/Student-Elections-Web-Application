package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.StageId;
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
    private CandidatureStageInfoResponse stageInfo;
    @NonNull
    private StageId stageId;

    public static class CandidatureStageResponseBuilder {
        public CandidatureStageResponseBuilder id(CandidatureStageId id){
            this.id = id;
            return this;
        }

        public CandidatureStageResponseBuilder id(String stringId){
            return id(CandidatureStageId.of(stringId));
        }

        public CandidatureStageResponseBuilder stageId(StageId id){
            this.stageId = id;
            return this;
        }

        public CandidatureStageResponseBuilder stageId(String stringId){
            return stageId(StageId.of(stringId));
        }
    }
}
