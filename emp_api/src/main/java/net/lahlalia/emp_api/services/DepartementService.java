package net.lahlalia.emp_api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lahlalia.emp_api.dtos.DepartementDto;
import net.lahlalia.emp_api.dtos.EmployeDto;
import net.lahlalia.emp_api.dtos.PageResponse;
import net.lahlalia.emp_api.entities.Departement;
import net.lahlalia.emp_api.exceptions.DepartementNotFoundException;
import net.lahlalia.emp_api.mappers.MapperDepartement;
import net.lahlalia.emp_api.repositories.DepartementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final MapperDepartement mapperDepartement;
    private final EmployeService employeService;

    public PageResponse<DepartementDto> getAllDepartements(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Departement> departements = departementRepository.findAll(pageable);
        List<DepartementDto> departementDtos = departements.stream().map(mapperDepartement::toDto).toList();
        return new PageResponse<>(
                departementDtos,
                departements.getNumber(),
                departements.getSize(),
                departements.getTotalElements(),
                departements.getTotalPages(),
                departements.isFirst(),
                departements.isLast()
        );
   }
    public List<DepartementDto> getAllDeps(){
        return departementRepository.findAll().stream().map(mapperDepartement::toDto).toList();

    }

    public List<EmployeDto> getEmployesByDepartement(Integer idDep){
        if(idDep == null){
            log.error("id is null");
            return null;
        }
        Optional<Departement> departementOptional = departementRepository.findById(idDep);
        if (departementOptional.isEmpty()) {
            log.error("Departement with id {} not found", idDep);
            return null; // or throw a DepartementNotFoundException
        }
        Departement departement = departementOptional.get();
        return employeService.getEmployesByDepartement(departement);
    }
    public DepartementDto getDepartementById(Integer idDep){
        if(idDep == null){
            log.error("id is null");
            return null;
        }
        Departement departement = departementRepository.findById(idDep).get();
        return mapperDepartement.toDto(departement);
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
