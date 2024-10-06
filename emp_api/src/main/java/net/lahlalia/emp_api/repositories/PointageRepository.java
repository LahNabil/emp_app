package net.lahlalia.emp_api.repositories;

import net.lahlalia.emp_api.entities.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointageRepository extends JpaRepository<Pointage, Integer> {
}
