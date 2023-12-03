package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@ToString
@Builder(toBuilder = true)
public class ElectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="election_id")
    private UUID id;

//     description text  not null,
    @Column(name = "description", nullable = false)
    private String description;

//    year int  not null,
    @Column(name = "year", nullable = false)
    private Integer year;

//    created_at timestamp  not null,
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

//    deadline timestamp  not null,
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

//    private    total_votes_count int not null default 0
    @Column(name = "total_votes_count", nullable = false)
    private Integer totalVotesCount;


    @OneToMany(mappedBy="stage")
    private Set<StageEntity> stages;

    @OneToMany(mappedBy = "candidature")
    private Set<CandidatureEntity> candidatures;


}
