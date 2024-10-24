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
    @NotEmpty(message = "Nom ne doit pas etre vide")
    @NotNull(message = "La valeur de ce champ est requise")
    private String nom;

    //private boolean archived;
}
