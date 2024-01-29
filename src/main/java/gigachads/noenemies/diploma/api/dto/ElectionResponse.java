package gigachads.noenemies.diploma.api.dto;


import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.Stage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResponse {
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
