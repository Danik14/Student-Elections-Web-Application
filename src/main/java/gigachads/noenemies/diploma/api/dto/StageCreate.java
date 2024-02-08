package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StageCreate {
    @NotNull
    private String description;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;

    @ToString.Exclude
    @JsonIgnore
    @NotNull
    private ElectionResponse election;
    @ToString.Exclude
    @JsonIgnore
    @NotNull
    private List<CandidatureStageResponse> candidatureStages;
}