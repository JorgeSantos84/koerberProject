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
@Table(name = "consult")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Consult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consult")
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    @JsonProperty("doctor ")
    @Schema(name = "doctor", description = "Doctor")
    private Doctor doctor;

//    @ManyToOne
//    @JoinColumn(name = "id_specialty", nullable = false)
//    @JsonProperty("specialty ")
//    @Schema(name = "specialty", description = "Specialty")
//    private ClinicSpecialty specialty;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    @JsonProperty("patient ")
    @Schema(name = "patient", description = "Patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_pathology", nullable = false)
    @JsonProperty("pathology ")
    @Schema(name = "pathology", description = "Pathology")
    private ClinicPathology pathology;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Transient
    @JsonProperty("symptoms")
    public Set<ClinicSymptom> getSymptoms() {
        return pathology != null ? pathology.getSymptoms() : Set.of();
    }

}
