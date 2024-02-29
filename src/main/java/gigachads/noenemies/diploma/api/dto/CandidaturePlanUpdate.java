package gigachads.noenemies.diploma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidaturePlanUpdate {
    private String description;
    private String slogan;
    private URI instagramLink;
    private URI telegramLink;
}
