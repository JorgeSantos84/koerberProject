package Koerber.TestProject.dto;


import Koerber.TestProject.model.ClinicSymptom;
import Koerber.TestProject.model.Consult;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO Class for find consults response
 */
public class FindConsultsResponseDTO {

    private List<ConsultResponseDTO> consults;

    private Set<ClinicSymptom> symptoms;
}
