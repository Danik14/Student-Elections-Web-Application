package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class UserEntity extends BaseEntity{
    @Column(name = "barcode", nullable = false)
    private String barcode;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name= "email", nullable = false)
    private String email;
    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<CandidatureEntity> candidatures;

    @ToString.Exclude
    @OneToMany(mappedBy = "elector")
    private List<VoteEntity> votes;
}
