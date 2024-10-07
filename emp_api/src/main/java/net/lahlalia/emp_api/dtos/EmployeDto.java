package net.lahlalia.emp_api.dtos;


import lombok.*;

import net.lahlalia.emp_api.entities.Employe;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeDto {


    private Integer idEmp;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private double salaireBase;
    private Integer idSuperviseur;
    private Integer idDep;

}
