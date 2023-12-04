package gigachads.noenemies.diploma.domain.model.Election;

import gigachads.noenemies.diploma.domain.model.candidature.Candidature;
import gigachads.noenemies.diploma.domain.model.stage.Stage;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
public class Election {
    @NonNull
    private ElectionId id;

    @NonNull
    private String description;

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
    private Set<Stage> stages;

    @ToString.Exclude
    @NonNull
    private Set<Candidature> candidatures;
}
