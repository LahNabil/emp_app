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
    private LocalDateTime dateEntre;
    private LocalDateTime dateSortie;
    private Integer idEmp;
}