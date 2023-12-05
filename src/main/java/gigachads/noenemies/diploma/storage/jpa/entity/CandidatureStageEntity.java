package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidature_stages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureStageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "candidature_stage_id")
    private UUID id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private StageEntity stage;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidature_id", nullable = false)
    private CandidatureEntity candidature;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidatureStage")
    private List<VoteEntity> votes;
}
