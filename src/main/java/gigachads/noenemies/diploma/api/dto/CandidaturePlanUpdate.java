package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.Candidature;
import lombok.*;

import java.net.URI;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CandidaturePlanUpdate {
    private final String description;
    private final String slogan;
    private final URI instagramLink;
    private final URI telegramLink;
}
