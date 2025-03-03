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
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    @JsonIgnore
    private Long idPatient;

    @Column(name = "patient_name", nullable = false, length = 255)
    @JsonProperty("patientName")
    @Schema(name = "patientName", description = "Name of patient", example = "Joana")
    private String patientName;

    @Column(name = "age", nullable = false)
    @JsonProperty("age")
    @Schema(name = "age", description = "Age of patient", example = "32")
    private int age;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;
}
