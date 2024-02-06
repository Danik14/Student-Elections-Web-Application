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
}
