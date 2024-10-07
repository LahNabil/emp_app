package net.lahlalia.emp_api.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartementDto {

    private Integer idDep;
    @NotEmpty(message = "100")
    @NotNull(message = "100")
    private String nom;
}
