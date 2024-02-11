package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gigachads.noenemies.diploma.domain.model.CandidatureId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@AllArgsConstructor
public class CandidatureResponse {
    @NonNull
    private final CandidatureId id;
    @NonNull
    private CandidaturePlanResponse plan;

    @JsonIgnoreProperties("photo")
    @NonNull
    private UserResponse approvedBy;
    @NonNull
    private UserResponse user;
}
