package gigachads.noenemies.diploma.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.ElectionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class ElectionResponse {
    @NonNull
    private final ElectionId id;
    @NonNull
    private final String description;
    @NonNull
    private final ElectionStatus status;
    @NonNull
    private final Integer year;
    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime deadline;
    @NonNull
    private final Integer totalVotesCount;
}
