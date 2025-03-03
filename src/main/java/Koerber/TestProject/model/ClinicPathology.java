package Koerber.TestProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "clinic_pathology")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClinicPathology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pathology")
    @JsonIgnore
    private Long id;

    @Column(name = "name_pathology", nullable = false)
    @JsonProperty("namePathology")
    @Schema(name = "namePathology", description = "Name of the pathology", example = "Pathology 1")
    private String namePathology;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "pathology_symptom",
            joinColumns = @JoinColumn(name = "id_pathology"),
            inverseJoinColumns = @JoinColumn(name = "id_symptom")
    )
    private Set<ClinicSymptom> symptoms;
}
