package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gigachads.noenemies.diploma.domain.model.StageId;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StageResponse {
    @NonNull
    private StageId id;
    @NonNull
    private String description;
    @NonNull
    private String status;
    private boolean votable;
    @NonNull
    private Integer number;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime deadline;
}
