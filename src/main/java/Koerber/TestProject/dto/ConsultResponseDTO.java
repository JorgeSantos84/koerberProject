package Koerber.TestProject.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConsultResponseDTO {

    private long consultId;
    private String doctor;
    private String specialty;
}
