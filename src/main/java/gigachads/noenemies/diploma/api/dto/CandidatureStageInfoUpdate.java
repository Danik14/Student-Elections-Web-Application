package gigachads.noenemies.diploma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureStageInfoUpdate {
    private String description;
    private String link1;
    private String link2;
}
