package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

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
    private final Integer year;
    @NonNull
    private final LocalDateTime createdAt;
    @NonNull
    private final LocalDateTime deadline;

    @NonNull
    private final List<Stage> stages;
}
