package net.lahlalia.emp_api.dtos;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeDto {


    private Integer idEmp;

    @NotEmpty(message = "nom ne peut pas être vide ")
    @NotNull(message = "La valeur de nom est requise.")
    private String nom;

    @NotEmpty(message = "prenom ne peut pas être vide ")
    @NotNull(message = "La valeur de prenom est requise.")
    private String prenom;

    @NotEmpty(message = "cin ne peut pas être vide ")
    @NotNull(message = "La valeur de cin est requise.")
    private String cin;

    @NotEmpty(message = "telephone ne peut pas être vide ")
    @NotNull(message = "La valeur de telephone est requise.")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Le numéro de téléphone doit commencer par 0 et contenir exactement 10 chiffres.")
    private String telephone;

    @DecimalMin(value = "0.0", inclusive = false)
    private double salaireBase;

    @NotNull(message = "Veuillez saisir un Superviseur")
    private Integer idSuperviseur;

    @NotNull(message = "Veuillez saisir un Departement")
    private Integer idDep;

    private String nomDep;

}
