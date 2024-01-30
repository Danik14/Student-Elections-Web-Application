package gigachads.noenemies.diploma.storage.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Table(name = "candidature_plans")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidaturePlanEntity extends BaseEntity {
    @Column(name = "description")
    private String description;
    @Column(name = "slogan")
    private String slogan;
    @Column(name = "instagramLinkLink")
    private URI instagramLink;
    @Column(name = "telegramLink")
    private URI telegramLink;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "candidature_id", nullable = false)
    private CandidatureEntity candidature;
}
