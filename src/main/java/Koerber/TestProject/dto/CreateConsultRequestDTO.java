package Koerber.TestProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO Class for a request to create a consult
 */
public class CreateConsultRequestDTO {

    @NotNull(message = "doctorId cannot be null")
    @JsonProperty("doctorId")
    @Schema(name = "doctorId", example = "1")
    private Long doctorId;

    @NotNull(message = "patientId cannot be null")
    @JsonProperty("patientId")
    @Schema(name = "patientId", example = "1")
    private Long patientId;

    @NotNull(message = "pathologyId cannot be null")
    @JsonProperty("pathologyId")
    @Schema(name = "pathologyId", example = "1")
    private Long pathologyId;

}
