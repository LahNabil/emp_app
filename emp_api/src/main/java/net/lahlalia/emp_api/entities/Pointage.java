package net.lahlalia.emp_api.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Pointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPoi;
    @NotEmpty(message = "100")
    @NotNull(message = "100")
    private LocalDateTime dateEntre;
    @NotEmpty(message = "100")
    @NotNull(message = "100")
    private LocalDateTime dateSortie;
    @ManyToOne
    private Employe employe;
}
