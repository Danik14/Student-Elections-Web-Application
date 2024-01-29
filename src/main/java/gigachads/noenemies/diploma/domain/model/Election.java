package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Election {
    @NonNull
    private ElectionId id;

    @NonNull
    private String description;

    @NonNull
    private Boolean active;

    @NonNull
    private Integer year;

    @NonNull
    private LocalDateTime createdAt;

    @NonNull
    private LocalDateTime deadline;

    @NonNull
    private Integer totalVotesCount;

    @ToString.Exclude
    @NonNull
    private List<Stage> stages;

    @ToString.Exclude
    @NonNull
    private List<Candidature> candidatures;
}
