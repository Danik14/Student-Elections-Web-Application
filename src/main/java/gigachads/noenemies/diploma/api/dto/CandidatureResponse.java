package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gigachads.noenemies.diploma.domain.model.CandidatureId;
import gigachads.noenemies.diploma.domain.model.UserId;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureResponse {
    @NonNull
    private CandidatureId id;
    @JsonIgnoreProperties("photo")
    @NonNull
    private UserId approvedById;
    @NonNull
    @JsonIgnoreProperties("photo")
    private UserResponse user;

    public static class CandidatureResponseBuilder {
        public CandidatureResponseBuilder id(CandidatureId id) {
            this.id = id;
            return this;
        }

        public CandidatureResponseBuilder id(String stringId) {
            return id(CandidatureId.of(stringId));
        }

        public CandidatureResponseBuilder approvedById(UserId id) {
            this.approvedById = id;
            return this;
        }

        public CandidatureResponseBuilder approvedById(String id) {
            return approvedById(UserId.of(id));
        }
    }
}
