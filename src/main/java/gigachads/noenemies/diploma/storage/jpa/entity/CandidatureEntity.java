package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@ToString
@Table(name = "candidatures")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CandidatureEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "approvedBy_id")
    private UserEntity approvedBy;
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToOne(mappedBy = "candidature")
    private CandidaturePlanEntity plan;
    @ToString.Exclude
    @OneToMany(mappedBy = "candidature")
    private List<CandidatureStageEntity> candidatureStages;
}
