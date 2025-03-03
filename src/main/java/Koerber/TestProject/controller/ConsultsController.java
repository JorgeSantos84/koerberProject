package Koerber.TestProject.controller;


import Koerber.TestProject.dto.CreateConsultRequestDTO;
import Koerber.TestProject.dto.FindConsultsDTO;
import Koerber.TestProject.dto.FindConsultsResponseDTO;
import Koerber.TestProject.model.Consult;
import Koerber.TestProject.service.ConsultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
