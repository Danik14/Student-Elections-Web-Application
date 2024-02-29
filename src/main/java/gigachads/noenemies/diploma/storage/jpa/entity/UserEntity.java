package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@SuperBuilder(toBuilder = true)
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
    @Column(name = "filePhotoName", nullable = false)
    private String filePhotoName;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<CandidatureEntity> candidatures;

    @ToString.Exclude
    @OneToMany(mappedBy = "elector")
    private List<VoteEntity> votes;

    @PrePersist
    public void onCreate() {
        filePhotoName = "";
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(getBarcode(), that.getBarcode()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && getRole() == that.getRole() && Objects.equals(getFilePhotoName(), that.getFilePhotoName()) && Objects.equals(getCandidatures(), that.getCandidatures()) && Objects.equals(getVotes(), that.getVotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBarcode(), getFirstName(), getLastName(), getEmail(), getRole(), getFilePhotoName(), getCandidatures(), getVotes());
    }
}
