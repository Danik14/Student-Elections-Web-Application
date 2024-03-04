package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gigachads.noenemies.diploma.domain.model.VoteId;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteResponse {
    @NonNull
    private VoteId id;

    @NonNull
    @JsonIgnoreProperties("photo")
    private UserResponse elector;

    @NonNull
    @JsonIgnoreProperties({"approvedBy", "plan"})
    private CandidatureResponse candidature;
}
