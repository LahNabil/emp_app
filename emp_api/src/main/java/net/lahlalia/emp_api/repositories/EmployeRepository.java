package net.lahlalia.emp_api.repositories;

import net.lahlalia.emp_api.entities.Departement;
import net.lahlalia.emp_api.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    List<Employe> findBySuperviseur(Employe superviseur);
    List<Employe> findByDepartement(Departement departement);
    Page<Employe> findAllByArchivedFalse(Pageable pageable);
}
