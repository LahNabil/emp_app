package net.lahlalia.emp_api.repositories;

import net.lahlalia.emp_api.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
