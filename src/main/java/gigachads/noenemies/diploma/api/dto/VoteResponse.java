package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gigachads.noenemies.diploma.domain.model.VoteId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Getter
public class VoteResponse {
    @NonNull
    private VoteId id;

    @NonNull
    @JsonIgnoreProperties("photo")
    private UserResponse elector;
}
