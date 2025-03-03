package Koerber.TestProject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO Class to create a consult
 */
public class ConsultRequestDTO {

    @NotNull(message = "doctorId cannot be null")
    private Long doctorId;

    @NotNull(message = "patientId cannot be null")
    private Long patientId;

    @NotNull(message = "pathologyId cannot be null")
    private Long pathologyId;

}
