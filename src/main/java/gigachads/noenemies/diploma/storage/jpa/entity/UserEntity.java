package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.domain.model.user.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name= "email")
    private String email;
    @Column(name = "role")
    private UserRole role;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<CandidatureEntity> candidatures;

    @ToString.Exclude
    @OneToMany(mappedBy = "elector")
    private List<VoteEntity> votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(barcode, that.barcode) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && role == that.role && Objects.equals(candidatures, that.candidatures) && Objects.equals(votes, that.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barcode, firstName, lastName, email, role, candidatures, votes);
    }
}
