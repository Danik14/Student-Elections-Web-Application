package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;
import java.util.UUID;

@Entity
public class UserEntity {
    @Id
    private UUID id;
    @OneToMany(mappedBy = "user")
    Set<CandidatureEntity> candidatures;

    @OneToMany(mappedBy = "elector")
    Set<VoteEntity> votes;
}
