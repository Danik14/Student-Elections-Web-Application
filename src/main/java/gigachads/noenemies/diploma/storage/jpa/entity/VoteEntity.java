package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@Table(name = "votes")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VoteEntity extends BaseEntity{
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "elector_id", nullable = false)
    private UserEntity elector;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidature_stage_id", nullable = false)
    private CandidatureStageEntity candidatureStage;
}
