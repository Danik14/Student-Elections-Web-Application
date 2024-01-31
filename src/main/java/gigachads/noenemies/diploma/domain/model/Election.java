package gigachads.noenemies.diploma.domain.model;

import gigachads.noenemies.diploma.domain.service.ElectionService;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(toBuilder = true)
public class Election {
    @NonNull
    private final ElectionId id;
    @NonNull
    private final String description;
    @NonNull
    private final ElectionStatus status;
    @NonNull
    private final Boolean active;
    @NonNull
    private final Integer year;
    @NonNull
    private final LocalDateTime createdAt;
    @NonNull
    private final LocalDateTime deadline;
    @NonNull
    private final Integer totalVotesCount;
    @ToString.Exclude
    @NonNull
    private final List<Stage> stages;
    @ToString.Exclude
    @NonNull
    private final List<Candidature> candidatures;
}
