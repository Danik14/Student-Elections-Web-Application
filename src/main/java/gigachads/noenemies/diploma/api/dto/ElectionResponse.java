package gigachads.noenemies.diploma.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.ElectionStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ElectionResponse {
    @NonNull
    private ElectionId id;
    @NonNull
    private String description;
    @NonNull
    private ElectionStatus status;
    @NonNull
    private Integer year;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime deadline;

    @NonNull
    private List<StageResponse> stages;
}
