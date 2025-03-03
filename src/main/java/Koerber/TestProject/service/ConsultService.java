package Koerber.TestProject.service;


import Koerber.TestProject.dto.*;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    /**
     * Find and filter specielties with more than two unique patients
     */
    public List<SpecialtyAndNumberPatientsResponseDTO> findSpecialtiesUniquePatients(){
        log.info("Retrieving Specialties with more than 2 unique patients...");
        List<Object[]> resultList = consultRepository.findUniqueSpecialtyAndPatient();
        HashMap<String, Integer> result = new HashMap<>();

        for(Object[] specialty: resultList){
            String specialtyName = (String) specialty[1];
            if(result.containsKey(specialtyName)){
                int total = result.get(specialtyName) + 1;
                result.put(specialtyName, total);
            } else {
                result.put(specialtyName, 1);
            }
        }

        return result.entrySet().stream()
                .filter(entry -> entry.getValue() > 2)
                .map(entry -> SpecialtyAndNumberPatientsResponseDTO.builder()
                        .specialtyName(entry.getKey())
                        .numberOfPatients(entry.getValue())
                        .build())
                .collect(Collectors.toList());

    }

    public Page<Patient> getPatients(FilterPatientRequestDTO filter, Pageable pageable) {
        log.info("Retrieving all patients sortedBy: {}, sortDirection: {}", filter.getSortBy(), filter.getSortDirection());
        Specification<Patient> spec = Specification.where(null);
        if (filter.getMinAge() >= 0 && filter.getMaxAge() >= filter.getMinAge()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("age"), filter.getMinAge(), filter.getMaxAge()));
        }

        return patientRepository.findAll(spec, pageable);
    }

}
