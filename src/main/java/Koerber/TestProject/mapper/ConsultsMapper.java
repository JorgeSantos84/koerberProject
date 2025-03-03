package Koerber.TestProject.mapper;

import Koerber.TestProject.dto.ConsultResponseDTO;
import Koerber.TestProject.model.Consult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultsMapper {

    public List<ConsultResponseDTO> mapFromConsultsToConsultResponseDTO(List<Consult> consultList) {
        return consultList.stream()
                .map(this::mapConsultToDTO)
                .collect(Collectors.toList());
    }

    private ConsultResponseDTO mapConsultToDTO(Consult consult) {
        return ConsultResponseDTO.builder()
                .consultId(consult.getId())
                .doctor(consult.getDoctor() != null ? consult.getDoctor().getDoctorName() : "Unknown")
                .specialty(consult.getDoctor() != null && consult.getDoctor().getSpecialty() != null
                        ? consult.getDoctor().getSpecialty().getNameSpecialty()
                        : "Unknown")
                .build();
    }
}
