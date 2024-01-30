package gigachads.noenemies.diploma.storage.jpa.entity;

import com.azure.core.annotation.Get;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidatures")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatureEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;

//    @Column(name = "plan", nullable = false)
//    private CandidaturePlanEntity plan;

    @Column(name = "approved", nullable = false)
    private boolean approved;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "approvedBy_id")
    private UserEntity approvedBy;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidature")
    private List<CandidatureStageEntity> candidatureStages;
}
