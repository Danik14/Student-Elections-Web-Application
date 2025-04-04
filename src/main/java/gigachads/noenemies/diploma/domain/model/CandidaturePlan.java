package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Builder
@Getter
public class CandidaturePlan {
    @NonNull
    private final UUID id;
    @NonNull
    private final String description;
    @NonNull
    private final String slogan;
    @NonNull
    private final String instagramLink;
    @NonNull
    private final String telegramLink;
}
