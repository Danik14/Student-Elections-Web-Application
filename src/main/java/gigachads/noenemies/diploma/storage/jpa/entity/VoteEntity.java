package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@ToString
@Entity
@Table(name = "votes")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteEntity extends BaseEntity{
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "elector_id", nullable = false)
    private UserEntity elector;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidature_stage_id", nullable = false)
    private CandidatureStageEntity candidatureStage;
}
