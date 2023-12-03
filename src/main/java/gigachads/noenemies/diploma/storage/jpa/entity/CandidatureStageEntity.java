package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidature_stages")
public class CandidatureStageEntity {
//       candidature_stage_id uuid primary key ,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "candidature_stage_id")
    private UUID id;
//    stage_id uuid references stages(stage_id) not null,

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private StageEntity stage;
//    candidature_id uuid references candidatures(candidature_id) not null

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidature_id", nullable = false)
    private CandidatureEntity candidature;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidatureStage")
    private Set<VoteEntity> votes;
}
