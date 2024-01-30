package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidature_stages")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureStageEntity extends BaseEntity {
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
