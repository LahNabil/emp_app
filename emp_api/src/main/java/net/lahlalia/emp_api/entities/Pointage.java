package net.lahlalia.emp_api.entities;


import jakarta.persistence.*;
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
    private LocalDateTime dateEntre;
    private LocalDateTime dateSortie;
    @ManyToOne
    private Employe employe;
}
