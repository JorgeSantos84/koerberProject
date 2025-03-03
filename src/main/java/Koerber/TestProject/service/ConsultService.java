package Koerber.TestProject.service;


import Koerber.TestProject.dto.ConsultResponseDTO;
import Koerber.TestProject.dto.CreateConsultRequestDTO;
import Koerber.TestProject.dto.FindConsultsDTO;
import Koerber.TestProject.dto.FindConsultsResponseDTO;
import Koerber.TestProject.exception.ResourceNotFoundException;
import Koerber.TestProject.mapper.ConsultsMapper;
import Koerber.TestProject.model.*;
import Koerber.TestProject.repository.ClinicPathologyRepository;
import Koerber.TestProject.repository.ConsultRepository;
import Koerber.TestProject.repository.DoctorRepository;
import Koerber.TestProject.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ConsultService {

    private final ConsultRepository consultRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ClinicPathologyRepository pathologyRepository;

    @Autowired
    private ConsultsMapper consultsMapper;

    public Consult createConsult(CreateConsultRequestDTO requestDTO) {
        log.info("Creating consult...");
        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        ClinicPathology pathology = pathologyRepository.findById(requestDTO.getPathologyId())
                .orElseThrow(() -> new ResourceNotFoundException("Pathology not found"));

        Consult consult = Consult.builder()
                .doctor(doctor)
                .patient(patient)
                .pathology(pathology)
                .build();

        return consultRepository.save(consult);
    }

    public FindConsultsResponseDTO findAllConsultsByPatient(FindConsultsDTO consultsDTO){
        log.info("Finding all consults for patient: [{}]", consultsDTO.getPatientId());
        long idPatient = consultsDTO.getPatientId();

        List<Consult> consultList = consultRepository.findByPatient_IdPatient(idPatient);
        Set<ClinicSymptom> clinicSymptoms = consultList.stream()
                .map(Consult::getPathology)
                .filter(Objects::nonNull)
                .flatMap(pathology -> pathology.getSymptoms().stream())
                .sorted(Comparator.comparingLong(ClinicSymptom::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<ConsultResponseDTO> responseDTOS = consultsMapper.mapFromConsultsToConsultResponseDTO(consultList);

        return FindConsultsResponseDTO.builder()
                .consults(responseDTOS)
                .symptoms(clinicSymptoms)
                .build();
    }


}
