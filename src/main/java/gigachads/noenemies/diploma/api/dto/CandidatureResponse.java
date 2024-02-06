package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.CandidatureId;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class CandidatureResponse {
    @NonNull
    private final CandidatureId id;
    @NonNull
    private CandidaturePlanResponse plan;
    private boolean approved;

    @ToString.Exclude
    @NonNull
    private UserResponse approvedBy;
    @NonNull
    private UserResponse user;
}
