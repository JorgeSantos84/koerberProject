package Koerber.TestProject.service;

import Koerber.TestProject.dto.ConsultRequestDTO;
import Koerber.TestProject.exception.ResourceNotFoundException;
import Koerber.TestProject.model.*;
import Koerber.TestProject.repository.ClinicPathologyRepository;
import Koerber.TestProject.repository.ConsultRepository;
import Koerber.TestProject.repository.DoctorRepository;
import Koerber.TestProject.repository.PatientRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultServiceTest {

        @Mock
        private ConsultRepository consultRepository;

        @Mock
        private DoctorRepository doctorRepository;

        @Mock
        private PatientRepository patientRepository;

        @Mock
        private ClinicPathologyRepository pathologyRepository;

        @InjectMocks
        private ConsultService consultService;

        private Doctor doctor;
        private Patient patient;
        private ClinicPathology pathology;

        @BeforeEach
        void setUp(){
            doctor = Doctor.builder()
                    .id(1L)
                    .doctorName("Maria")
                    .specialty(ClinicSpecialty
                            .builder()
                            .idSpecialty(1L)
                            .nameSpecialty("Ophthalmology")
                            .build())
                    .build();

            patient =  Patient.builder()
                    .idPatient(1L)
                    .patientName("Manunel")
                    .age(53)
                    .build();

            pathology = ClinicPathology.builder()
                    .id(1L)
                    .namePathology("Ophthalmology")
                    .build();
        }

        @Test
        void shouldCreateConsultSuccessfully() {
            ConsultRequestDTO requestDTO = new ConsultRequestDTO(1L, 1L, 1L);

            Consult expectedConsult = Consult.builder()
                    .doctor(doctor)
                    .patient(patient)
                    .pathology(pathology)
                    .build();

            when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
            when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
            when(pathologyRepository.findById(1L)).thenReturn(Optional.of(pathology));

            when(consultRepository.save(any(Consult.class))).thenReturn(expectedConsult);

            Consult actualConsult = consultService.createConsult(requestDTO);

            assertNotNull(actualConsult);
            assertEquals(doctor, actualConsult.getDoctor());
            assertEquals(patient, actualConsult.getPatient());
            assertEquals(pathology, actualConsult.getPathology());

            verify(consultRepository).save(any(Consult.class));
        }

        @Test
        void shouldThrowExceptionWhenDoctorNotFound() {
            ConsultRequestDTO requestDTO = new ConsultRequestDTO(1L, 1L, 1L);

            when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> consultService.createConsult(requestDTO));

            verify(consultRepository, never()).save(any(Consult.class));
        }

        @Test
        void shouldThrowExceptionWhenPatientNotFound() {
            ConsultRequestDTO requestDTO = new ConsultRequestDTO(1L, 2L, 3L);

            when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
            when(patientRepository.findById(2L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> consultService.createConsult(requestDTO));

            verify(consultRepository, never()).save(any(Consult.class));
        }


}