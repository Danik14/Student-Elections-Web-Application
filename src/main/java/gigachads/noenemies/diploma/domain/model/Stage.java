package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@Builder
public class Stage {
    @NonNull
    private final StageId id;
    @NonNull
    private final String description;
    @NonNull
    private final StageStatus status;
    @NonNull
    private final Integer number;
    @NonNull
    private final LocalDateTime deadline;
}
