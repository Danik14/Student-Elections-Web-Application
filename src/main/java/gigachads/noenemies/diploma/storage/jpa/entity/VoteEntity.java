package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Entity
@Table(name = "votes")
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="vote_id")
//     vote_id uuid primary key,
    private UUID id;

//    elector_id uuid references users(user_id) not null,
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="elector_id",nullable = false)
    private UserEntity elector;
//    candidature_stage_id uuid references candidature_stages(candidature_stage_id) not null
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "candidature_stage_id", nullable = false)
    private CandidatureStageEntity candidatureStage;
}
