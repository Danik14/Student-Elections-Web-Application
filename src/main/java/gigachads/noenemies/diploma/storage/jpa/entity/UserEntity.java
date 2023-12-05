package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    List<CandidatureEntity> candidatures;

    @ToString.Exclude
    @OneToMany(mappedBy = "elector")
    List<VoteEntity> votes;
}
