package net.lahlalia.emp_api.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmp;
    private String nom;
    private String prenom;
    private String CIN;
    private String telephone;
    private double salaireBase;

    @OneToMany(mappedBy = "employe")
    private List<Pointage> pointageList;

    @ManyToOne
    @JoinColumn(name="superviseur_id")
    private Employe superviseur;

    @OneToMany(mappedBy = "superviseur")
    private List<Employe> employes;

    @ManyToOne
    @JoinColumn(name = "idDep")
    private Departement departement;
}
