package gigachads.noenemies.diploma.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureStagePlanResponse {
    @NonNull
    private String description;
    @NonNull
    private String link1;
    @NonNull
    private String link2;
}
