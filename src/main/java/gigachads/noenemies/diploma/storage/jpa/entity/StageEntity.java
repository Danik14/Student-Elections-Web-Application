package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "stages")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stage_id")
    private UUID id;

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
