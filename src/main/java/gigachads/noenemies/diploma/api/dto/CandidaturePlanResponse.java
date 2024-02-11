package gigachads.noenemies.diploma.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class CandidaturePlanResponse {
    @NonNull
    private final String description;
    @NonNull
    private final String slogan;
    @NonNull
    private final String instagramLink;
    @NonNull
    private final String telegramLink;
}

