package Koerber.TestProject.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FilterPatientRequestDTO {

    @NotNull
    private int minAge;
    @NotNull
    private int maxAge;
    @NotNull
    private String sortBy;
    @NotNull
    private String sortDirection;
    @NotNull
    private int size;
}
