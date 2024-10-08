package net.lahlalia.emp_api.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmp;

    @NotEmpty(message = "100")
    @NotNull(message = "100")
    private String nom;

    @NotEmpty(message = "101")
    @NotNull(message = "101")
    private String prenom;

    @NotEmpty(message = "102")
    @NotNull(message = "102")
    private String cin;

    @NotEmpty(message = "103")
    @NotNull(message = "103")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Le numéro de téléphone doit commencer par 0 et contenir exactement 10 chiffres.")
    private String telephone;


    @NotNull(message = "104")
    private double salaireBase;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "employe")
    private List<Pointage> pointageList;

    @ManyToOne
    @JoinColumn(name="superviseur_id")
    private Employe superviseur;

    @OneToMany(mappedBy = "superviseur")
    private List<Employe> employes;

    @ManyToOne
    @JoinColumn(name = "idDep")
    @NotNull(message = "105")
    private Departement departement;
}
