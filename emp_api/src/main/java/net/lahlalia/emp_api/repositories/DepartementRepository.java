package net.lahlalia.emp_api.repositories;

import net.lahlalia.emp_api.entities.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

    Page<Departement> findAllByArchivedFalse(Pageable pageable);
}
