package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.StageId;
import gigachads.noenemies.diploma.domain.model.StageStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StageResponse {
    @NonNull
    private StageId id;
    @NonNull
    private ElectionId electionId;
    @NonNull
    private String description;
    @NonNull
    private StageStatus status;
    private boolean votable;
    @NonNull
    private Integer number;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime deadline;

    public static class StageResponseBuilder {
        public StageResponseBuilder id(StageId id) {
            this.id = id;
            return this;
        }

        public StageResponseBuilder id(String stringId) {
            return id(StageId.of(stringId));
        }
    }
}
