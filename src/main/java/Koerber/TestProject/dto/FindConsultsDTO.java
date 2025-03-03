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
 * DTO Class for a request to find consults of a patient
 */
public class FindConsultsDTO {

    @NotNull(message = "patientId cannot be null")
    @JsonProperty("patientId")
    @Schema(name = "patientId", example = "1")
    private Long patientId;
}
