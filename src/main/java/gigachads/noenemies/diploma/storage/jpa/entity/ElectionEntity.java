package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "elections")
public class ElectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "election_id")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "total_votes_count", nullable = false)
    private Integer totalVotesCount;

    @ToString.Exclude
    @OneToMany(mappedBy = "election")
    private List<StageEntity> stages;

    @ToString.Exclude
    @OneToMany(mappedBy = "election")
    private List<CandidatureEntity> candidatures;
}
