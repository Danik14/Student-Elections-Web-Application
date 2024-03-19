package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@ToString
@Table(name = "candidature_stages")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureStageEntity extends BaseEntity {
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private StageEntity stage;

    @ManyToOne
    @JoinColumn(name = "candidature_id", nullable = false)
    private CandidatureEntity candidature;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidatureStage")
    private List<VoteEntity> votes;

    @OneToOne(mappedBy = "candidatureStage")
    private CandidatureStageInfoEntity stagePlan;
}
