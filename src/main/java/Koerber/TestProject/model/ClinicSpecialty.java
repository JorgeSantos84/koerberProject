package Koerber.TestProject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "clinic_specialty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Clinic Specialty
 */
public class ClinicSpecialty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_specialty")
    @JsonIgnore
    private Long idSpecialty;

    @Column(name = "name_specialty", unique = true, nullable = false)
    @JsonProperty("nameSpecialty")
    @Schema(name = "nameSpecialty", description = "Name of the clinic Specialty", example = "Dermatology")
    private String nameSpecialty;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;
}
