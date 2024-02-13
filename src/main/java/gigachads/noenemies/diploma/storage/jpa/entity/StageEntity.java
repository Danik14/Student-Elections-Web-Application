package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.domain.model.StageStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Table(name = "stages")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StageEntity extends BaseEntity{
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StageStatus status;
    @Column(name = "number", nullable = false)
    private Integer number;
    @Column(name = "is_votable", nullable = false)
    private boolean votable;
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "election_id")
    private ElectionEntity election;

    @ToString.Exclude
    @OneToMany(mappedBy = "stage")
    private List<CandidatureStageEntity> candidatureStages;

    @PrePersist
    @Override
    public void onCreate() {
        super.onCreate();

        if (number == null) {
            calculateAndSetNextStageNumber();
        }
    }

    private void calculateAndSetNextStageNumber() {
        if (election != null) {
            this.number = findNextStageNumberForElection(election);
        }
    }

    private int findNextStageNumberForElection(ElectionEntity election) {
        int maxNumber = election.getStages().stream()
                .mapToInt(StageEntity::getNumber)
                .max()
                .orElse(0);

        return maxNumber + 1;
    }
}
