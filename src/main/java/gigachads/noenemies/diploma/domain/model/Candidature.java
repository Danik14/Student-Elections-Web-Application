package gigachads.noenemies.diploma.domain.model;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidaturePlanEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
public class Candidature {
    @NonNull
    private final CandidatureId id;
    @NonNull
    private CandidaturePlan plan;
    private boolean approved;

    @ToString.Exclude
    @NonNull
    private User approvedBy;
    @ToString.Exclude
    @NonNull
    private User user;
}
