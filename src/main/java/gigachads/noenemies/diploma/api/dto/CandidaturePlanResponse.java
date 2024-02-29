package gigachads.noenemies.diploma.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidaturePlanResponse {
    @NonNull
    private String description;
    @NonNull
    private String slogan;
    @NonNull
    private String instagramLink;
    @NonNull
    private String telegramLink;
}

