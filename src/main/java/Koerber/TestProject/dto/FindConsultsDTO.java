package Koerber.TestProject.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO Class for a request to find consults of a patient
 */
public class FindConsultsDTO {

    @NotNull(message = "patientId cannot be null")
    private Long patientId;
}
