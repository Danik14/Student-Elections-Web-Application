package gigachads.noenemies.diploma.storage.jpa.entity;

import gigachads.noenemies.diploma.storage.jpa.converter.StringURIConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
    @OneToOne(mappedBy = "plan")
    private CandidatureEntity candidature;
}
