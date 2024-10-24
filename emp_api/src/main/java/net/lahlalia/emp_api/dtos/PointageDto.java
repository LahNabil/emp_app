package net.lahlalia.emp_api.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.lahlalia.emp_api.entities.Employe;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointageDto {


    private Integer idPoi;
    @NotNull(message = "Veuillez saisir la date d'entrée")
    private LocalDateTime dateEntre;
    @NotNull(message = "Veuillez saisir la date de sortie")
    private LocalDateTime dateSortie;
    @NotNull(message = "Veuillez saisir un Employé")
    private Integer idEmp;
    private String nomEmp;

}
