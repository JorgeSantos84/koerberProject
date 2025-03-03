package Koerber.TestProject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "clinic_symptom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ClinicSymptom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_symptom")
    private Long id;

    @Column(name = "name_symptom ", nullable = false)
    @JsonProperty("nameSymptom ")
    @Schema(name = "nameSymptom", description = "Name of the symptom", example = "Symptom 1 Description")
    private String nameSymptom;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;
}
