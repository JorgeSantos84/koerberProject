package Koerber.TestProject.service;


import Koerber.TestProject.dto.ConsultRequestDTO;
import Koerber.TestProject.exception.ResourceNotFoundException;
import Koerber.TestProject.model.ClinicPathology;
import Koerber.TestProject.model.Consult;
import Koerber.TestProject.model.Doctor;
import Koerber.TestProject.model.Patient;
import Koerber.TestProject.repository.ClinicPathologyRepository;
import Koerber.TestProject.repository.ConsultRepository;
import Koerber.TestProject.repository.DoctorRepository;
import Koerber.TestProject.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultService {

    private final ConsultRepository consultRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ClinicPathologyRepository pathologyRepository;

    public Consult createConsult(ConsultRequestDTO requestDTO){
        log.info("Starting creation of a consult...");

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

}
