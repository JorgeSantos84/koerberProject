package Koerber.TestProject.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consults")
@Tag(name = "Consults", description = "Endpoint to manage consults")
@RequiredArgsConstructor
public class ConsultsController {

    @Operation(summary = "Just a hello world", description = "hello world")
    @GetMapping("/hello-world")
    public ResponseEntity<String> returnHelloWorld(){

        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }
}
