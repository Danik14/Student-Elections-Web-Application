package gigachads.noenemies.diploma.storage.jpa.entity;


import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@ToString
@Table(name = "candidature_stage_plans")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CandidatureStagePlanEntity extends BaseEntity {
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "link1", nullable = false)
    private String link1;

    @Column(name = "link2", nullable = false)
    private String link2;

    @Column(name = "link3", nullable = false)
    private String link3;

    @JoinColumn(name = "candidature_stage_id")
    @OneToOne
    private CandidatureStageEntity candidatureStage;
}
