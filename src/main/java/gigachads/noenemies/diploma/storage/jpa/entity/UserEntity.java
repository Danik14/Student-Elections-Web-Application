package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.domain.model.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
@SuperBuilder
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
    @Column(name = "picturePath", nullable = false)
    private String picturePath;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<CandidatureEntity> candidatures;

    @ToString.Exclude
    @OneToMany(mappedBy = "elector")
    private List<VoteEntity> votes;

    @PrePersist
    public void onCreate() {
        picturePath = "";
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }
}
