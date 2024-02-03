package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.*;
import lombok.*;

import java.util.List;

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
