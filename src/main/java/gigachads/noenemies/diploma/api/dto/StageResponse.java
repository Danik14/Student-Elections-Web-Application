package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gigachads.noenemies.diploma.domain.model.StageId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
public class StageResponse {
    @NonNull
    private final StageId id;
    @NonNull
    private final String description;
    @NonNull
    private final String status;
    @NonNull
    private final Integer number;
    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime deadline;
}
