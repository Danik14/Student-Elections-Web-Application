package gigachads.noenemies.diploma.domain.model;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.net.URI;
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
    private final URI instagramLink;
    @NonNull
    private final URI telegramLink;

    @ToString.Exclude
    private final Candidature candidature;
}
