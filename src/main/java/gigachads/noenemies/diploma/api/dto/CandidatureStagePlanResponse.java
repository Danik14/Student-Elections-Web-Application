package gigachads.noenemies.diploma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@AllArgsConstructor
public class CandidatureStagePlanResponse {
    @NonNull
    private String description;
    @NonNull
    private String link1;
    @NonNull
    private String link2;
}
