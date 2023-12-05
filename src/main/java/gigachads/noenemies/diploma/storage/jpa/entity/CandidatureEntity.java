package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidatures")
public class CandidatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "candidature_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private ElectionEntity election;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidature")
    private List<CandidatureStageEntity> candidatureStages;
}
