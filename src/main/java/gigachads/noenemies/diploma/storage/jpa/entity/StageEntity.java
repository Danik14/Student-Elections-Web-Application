package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "stages")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageEntity extends BaseEntity{
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;

    @ToString.Exclude
    @OneToMany(mappedBy = "stage")
    private List<CandidatureStageEntity> candidatureStages;
}
