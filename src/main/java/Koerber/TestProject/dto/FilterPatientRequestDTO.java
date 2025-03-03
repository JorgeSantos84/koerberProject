package Koerber.TestProject.dto;


import Koerber.TestProject.model.enums.SortByPatients;
import Koerber.TestProject.model.enums.SortDirection;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FilterPatientRequestDTO {

    @NotNull
    @JsonProperty("minAge")
    @Schema(name = "minAge", example = "1")
    private int minAge;
    @NotNull
    @JsonProperty("maxAge")
    @Schema(name = "maxAge", example = "50")
    private int maxAge;
    @NotNull
    @Schema(name = "sortBy", example = "AGE")
    private SortByPatients sortBy;
    @NotNull
    @JsonProperty("sortDirection")
    @Schema(name = "sortDirection", example = "DESC")
    private SortDirection sortDirection;
    @NotNull
    @JsonProperty("size")
    @Schema(name = "size", example = "10")
    private int size;
}
