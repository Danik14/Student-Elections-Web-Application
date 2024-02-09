package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.domain.model.ElectionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Entity
@ToString
@Table(name = "elections")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ElectionEntity extends BaseEntity {
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ElectionStatus status;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @ToString.Exclude
    @OneToMany(mappedBy = "election")
    private List<StageEntity> stages;
    @ToString.Exclude
    @OneToMany(mappedBy = "election")
    private List<CandidatureEntity> candidatures;

    @PrePersist
    public void prePersist() {
        year = Year.now().getValue();
        status = ElectionStatus.CREATED;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }
}
