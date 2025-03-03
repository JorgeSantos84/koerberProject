package Koerber.TestProject.controller;


import Koerber.TestProject.dto.ConsultRequestDTO;
import Koerber.TestProject.model.Consult;
import Koerber.TestProject.service.ConsultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<Consult> createConsult(@Valid @RequestBody ConsultRequestDTO requestDTO){
        Consult consult = consultService.createConsult(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(consult);
    }
}
