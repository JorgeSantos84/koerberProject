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
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    @JsonIgnore
    private Long id;

    @Column(name = "doctor_name", nullable = false)
    @JsonProperty("doctorName")
    @Schema(name = "doctorName", description = "Name of the doctor", example = "Maria")
    private String doctorName;

    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false)
    @Schema(name = "specialty", description = "Specialty of the doctor")
    @JsonProperty("specialty")
    private ClinicSpecialty specialty;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;
}
