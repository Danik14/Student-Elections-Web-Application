package gigachads.noenemies.diploma.storage.jpa.entity;

import com.azure.core.annotation.Get;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidatures")
@Getter
@SuperBuilder
@NoArgsConstructor
public class CandidatureEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;

    @Column(name = "approved", nullable = false)
    private boolean approved;

    @OneToOne
    @JoinColumn(name = "candidature_plan_id")
    private CandidaturePlanEntity plan;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "approvedBy_id")
    private UserEntity approvedBy;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidature")
    private List<CandidatureStageEntity> candidatureStages;
}
