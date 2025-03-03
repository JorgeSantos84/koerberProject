package Koerber.TestProject.controller;

import Koerber.TestProject.dto.CreateConsultRequestDTO;
import Koerber.TestProject.model.*;
import Koerber.TestProject.service.ConsultService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ConsultsController.class)
@AutoConfigureMockMvc(addFilters = false)
class ConsultsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsultService consultService;

    private CreateConsultRequestDTO requestDTO;
    private Consult consult;
    private Doctor doctor;
    private Patient patient;
    private ClinicPathology  pathology;

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


        requestDTO = CreateConsultRequestDTO.builder()
                .doctorId(1L)
                .patientId(1L)
                .pathologyId(1L)
                .build();

        consult = Consult.builder()
                .doctor(doctor)
                .patient(patient)
                .pathology(pathology)
                .build();

        pathology = ClinicPathology.builder()
                .id(1L)
                .namePathology("Ophthalmology")
                .build();

        when(consultService.createConsult(any(CreateConsultRequestDTO.class))).thenReturn(consult);
    }


    @Test
    void createConsultShouldReturn201() throws Exception{
        mockMvc.perform(post("/v1/consults/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.doctor.doctorName").value("Maria"));
    }
}