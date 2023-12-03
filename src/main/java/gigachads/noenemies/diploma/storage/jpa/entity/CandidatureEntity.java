package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidatures")
public class CandidatureEntity {
    // candidature_id uuid primary key,
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "candidature_id")
    private UUID id;
    // election_id uuid references elections(election_id) not null,
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;
    // user_id uuid references users(user_id) not null
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ToString.Exclude
    @OneToMany(mappedBy = "candidature")
    private Set<CandidatureStageEntity> candidatureStages;
}
