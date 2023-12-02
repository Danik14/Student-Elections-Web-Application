package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class StageEntity {
//       stage_id uuid primary key,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stage_id")
    private UUID id;
//    description text  not null,
    @Column(name = "description", nullable = false)
    private String description;
//    deadline timestamp  not null,
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;
//    election_id uuid references elections(election_id) not null
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;

    @OneToMany(mappedBy = "stage")
    private Set<CandidatureStageEntity> candidatureStages;
}
