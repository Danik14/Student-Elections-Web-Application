package gigachads.noenemies.diploma.storage.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@ToString
@Table(name = "candidature_stage_info")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CandidatureStageInfoEntity extends BaseEntity {
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "link1", nullable = false)
    private String link1;

    @Column(name = "link2", nullable = false)
    private String link2;

    @ToString.Exclude
    @JoinColumn(name = "candidature_stage_id")
    @OneToOne
    private CandidatureStageEntity candidatureStage;
}
