package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.Candidature;
import lombok.*;

import java.net.URI;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CandidaturePlanUpdate {
    private String description;
    private String slogan;
    private URI instagramLink;
    private URI telegramLink;
}
