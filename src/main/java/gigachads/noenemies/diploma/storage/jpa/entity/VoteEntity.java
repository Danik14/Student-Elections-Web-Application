package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"elector_id", "candidature_stage_id"})
})
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
