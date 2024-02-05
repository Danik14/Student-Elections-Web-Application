package gigachads.noenemies.diploma.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.Stage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ElectionResponse {
    @NonNull
    private final ElectionId id;
    @NonNull
    private final String description;
    @NonNull
    private final Boolean active;
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
