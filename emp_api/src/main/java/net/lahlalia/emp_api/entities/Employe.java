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

    @Column(unique = true)
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String cin;
    @Column(unique = true)
    private String telephone;
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
    private Departement departement;
}
