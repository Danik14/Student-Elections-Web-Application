package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@ToString
@Table(name = "candidature_plans")
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CandidaturePlanEntity extends BaseEntity {
    @Column(name = "description")
    private String description;
    @Column(name = "slogan")
    private String slogan;
    @Column(name = "instagramLink")
    private String instagramLink;
    @Column(name = "telegramLink")
    private String telegramLink;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "candidature_id")
    private CandidatureEntity candidature;
}
