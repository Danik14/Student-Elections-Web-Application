package gigachads.noenemies.diploma.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureStageInfoUpdate {
    private String description;
    private String link1;
    private String link2;
}
