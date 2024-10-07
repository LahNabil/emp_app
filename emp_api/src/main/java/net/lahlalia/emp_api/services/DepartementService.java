package net.lahlalia.emp_api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lahlalia.emp_api.dtos.DepartementDto;
import net.lahlalia.emp_api.entities.Departement;
import net.lahlalia.emp_api.exceptions.DepartementNotFoundException;
import net.lahlalia.emp_api.mappers.MapperDepartement;
import net.lahlalia.emp_api.repositories.DepartementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final MapperDepartement mapperDepartement;

    public List<DepartementDto> getAllDepartements(){
        return departementRepository.findAll().stream().map(mapperDepartement::toDto).toList();
    }

    public DepartementDto saveDepartement(DepartementDto dto){
        if(dto == null){
            log.error("Departement null");
            return null;
        }
        Departement departement = mapperDepartement.toEntity(dto);
        Departement savedDepartement = departementRepository.save(departement);
        return mapperDepartement.toDto(savedDepartement);
    }
    public DepartementDto updateDepartement(Integer id, DepartementDto departementDto) throws DepartementNotFoundException {
        Departement existingDep = departementRepository.findById(id)
                .orElseThrow(() -> new DepartementNotFoundException("Departement with ID " + id + " not found"));

        existingDep.setNom(departementDto.getNom());
        Departement updatedDep = departementRepository.save(existingDep);

        return mapperDepartement.toDto(updatedDep);


    }

    public Boolean deleteDepartement(Integer idDep) throws DepartementNotFoundException {
        Optional<Departement> departement = departementRepository.findById(idDep);
        if(departement.isEmpty()) {
            throw new DepartementNotFoundException("departement not found with specific id : " + idDep);
        }
        departementRepository.deleteById(idDep);
        return true;
    }


}
