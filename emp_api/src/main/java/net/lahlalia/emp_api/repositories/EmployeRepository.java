package net.lahlalia.emp_api.repositories;

import net.lahlalia.emp_api.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {
}
