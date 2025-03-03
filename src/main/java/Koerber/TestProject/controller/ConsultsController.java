package Koerber.TestProject.controller;


import Koerber.TestProject.dto.*;
import Koerber.TestProject.model.Consult;
import Koerber.TestProject.model.PagedResponse;
import Koerber.TestProject.model.Patient;
import Koerber.TestProject.model.enums.SortByPatients;
import Koerber.TestProject.model.enums.SortDirection;
import Koerber.TestProject.service.ConsultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
@Tag(name = "Consults", description = "Endpoint to manage consults")
@RequiredArgsConstructor
public class ConsultsController {

    @Autowired
    private ConsultService consultService;

    @Operation(summary = "Create a consult", description = "This endpoint is used to create new consults")
    @PostMapping("/consults/create")
    public ResponseEntity<Consult> createConsult(@Valid @RequestBody CreateConsultRequestDTO requestDTO){
        Consult consult = consultService.createConsult(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consult);
    }

    @Operation(summary = "Find consults", description = "Return a list of consults for a patient")
    @PostMapping("/consults/find-all")
    public ResponseEntity<FindConsultsResponseDTO> findAllConsults(@Valid @RequestBody FindConsultsDTO requestDTO){
        FindConsultsResponseDTO consult = consultService.findAllConsultsByPatient(requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(consult);
    }

    @Operation(summary = "Find specialties with more than 2 unique patients", description = "Return a list of specialties with more then 2 unique patients")
    @GetMapping("/specialties/filter")
    public ResponseEntity<List<SpecialtyAndNumberPatientsResponseDTO>> getSpecialtiesWithFilter(){
        List<SpecialtyAndNumberPatientsResponseDTO> consult = consultService.findSpecialtiesUniquePatients();
        return ResponseEntity.status(HttpStatus.OK).body(consult);
    }

    @Operation(summary = "Get all patients", description = "Return a list of patients")
    @PostMapping("/patients/get-all")
    public ResponseEntity<PagedResponse<Patient>> getSpecialtiesWithFilter
            (@Valid @RequestBody FilterPatientRequestDTO filterPatientRequestDTO){
        Sort.Direction direction = Sort.Direction.fromString(filterPatientRequestDTO.getSortDirection().toString());
        String fromEnumSort = SortByPatients.fromString(filterPatientRequestDTO.getSortBy().toString());
        Pageable pageable = PageRequest.of(0, filterPatientRequestDTO.getSize(), direction, fromEnumSort);
        Page<Patient> patients = consultService.getPatients(
                filterPatientRequestDTO,
                pageable
        );

        return ResponseEntity.status(HttpStatus.OK).body(new PagedResponse<>(patients));
    }




}
