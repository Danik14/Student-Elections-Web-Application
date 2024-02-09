package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.storage.jpa.converter.StringURIConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.net.URI;

@Entity
@ToString
@Table(name = "candidature_plans")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CandidaturePlanEntity extends BaseEntity {
    @Column(name = "description")
    private String description;
    @Column(name = "slogan")
    private String slogan;
    @Column(name = "instagramLink")
    @Convert(converter = StringURIConverter.class)
    private URI instagramLink;
    @Column(name = "telegramLink")
    @Convert(converter = StringURIConverter.class)
    private URI telegramLink;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "candidature_id")
    private CandidatureEntity candidature;
}
