package Koerber.TestProject.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SpecialtyAndNumberPatientsResponseDTO {

    private String specialtyName;
    private Integer numberOfPatients;
}
