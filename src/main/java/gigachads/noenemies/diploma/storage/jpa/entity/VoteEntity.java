package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.UUID;

@ToString
@Entity
@Table(name = "votes")
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vote_id")
    private UUID id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "elector_id", nullable = false)
    private UserEntity elector;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidature_stage_id", nullable = false)
    private CandidatureStageEntity candidatureStage;
}
