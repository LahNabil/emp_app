package net.lahlalia.emp_api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lahlalia.emp_api.dtos.EmployeDto;
import net.lahlalia.emp_api.dtos.PageResponse;
import net.lahlalia.emp_api.entities.Departement;
import net.lahlalia.emp_api.entities.Employe;
import net.lahlalia.emp_api.exceptions.EmployeNotFoundException;
import net.lahlalia.emp_api.mappers.MapperEmploye;
import net.lahlalia.emp_api.repositories.DepartementRepository;
import net.lahlalia.emp_api.repositories.EmployeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeService {

    private final MapperEmploye mapperEmploye;
    private final EmployeRepository employeRepository;
    private final DepartementRepository departementRepository;

    public List<EmployeDto> getAllEmp(){
        return employeRepository.findAll().stream().map(mapperEmploye::toDto).toList();
    }
    public PageResponse<EmployeDto> getAllEmployes(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Employe> employes = employeRepository.findAll(pageable);
        List<EmployeDto> employeDtos = employes.stream()
                .map(employe-> {
                    EmployeDto dto = mapperEmploye.toDto(employe);
                    dto.setIdDep(employe.getDepartement() != null ? employe.getDepartement().getIdDep() : null);
                    dto.setNomDep(employe.getDepartement()!= null ? employe.getDepartement().getNom() : null);
                    return dto;
                }).toList();
        return new PageResponse<>(
                employeDtos,
                employes.getNumber(),
                employes.getSize(),
                employes.getTotalElements(),
                employes.getTotalPages(),
                employes.isFirst(),
                employes.isLast()
        );
     }

    public EmployeDto saveEmploye(EmployeDto dto) {
        if (dto == null) {
            log.error("Employe null");
            return null;
        }
        Employe employe = mapperEmploye.toEntity(dto);
        if (dto.getIdDep() != null) {
            departementRepository.findById(dto.getIdDep()).ifPresent(employe::setDepartement);
        }
        if (dto.getIdSuperviseur() != null) {
            employeRepository.findById(dto.getIdSuperviseur()).ifPresent(employe::setSuperviseur);
        }

        Employe savedEmploye = employeRepository.save(employe);

        // Convert to DTO and return
        return mapperEmploye.toDto(savedEmploye);
    }
    public EmployeDto updateEmploye(Integer id, EmployeDto employeDto) throws EmployeNotFoundException {
        Employe existingEmp = employeRepository.findById(id)
                .orElseThrow(() -> new EmployeNotFoundException("Employe with ID " + id + " not found"));

        Departement dep = departementRepository.findById(employeDto.getIdDep()).get();
        Employe superviseur = employeRepository.findById(employeDto.getIdSuperviseur()).get();

        existingEmp.setNom(employeDto.getNom());
        existingEmp.setCin(employeDto.getCin());
        existingEmp.setPrenom(employeDto.getPrenom());
        existingEmp.setTelephone(employeDto.getTelephone());
        existingEmp.setSalaireBase(employeDto.getSalaireBase());
        existingEmp.setDepartement(dep);
        existingEmp.setSuperviseur(superviseur);
        Employe updatedEmp = employeRepository.save(existingEmp);

        return mapperEmploye.toDto(updatedEmp);


    }
    public EmployeDto getEmployeById(Integer idEmp){
        if(idEmp == null){
            log.error("id is null");
            return null;
        }
        Employe employe = employeRepository.findById(idEmp).get();
        EmployeDto dto = mapperEmploye.toDto(employe);
        dto.setIdDep(employe.getDepartement() != null ? employe.getDepartement().getIdDep() : null);
        dto.setIdSuperviseur(employe.getSuperviseur() != null ? employe.getSuperviseur().getIdEmp() : null);
        return dto;
    }

    public Boolean deleteEmp(Integer idEmp) throws EmployeNotFoundException {
        Employe employe = employeRepository.findById(idEmp)
                .orElseThrow(() -> new EmployeNotFoundException("Employe not found with specific id: " + idEmp));


        List<Employe> supervisees = employeRepository.findBySuperviseur(employe);

        for (Employe supervisee : supervisees) {
            supervisee.setSuperviseur(null);
            employeRepository.save(supervisee);
        }

        employeRepository.deleteById(idEmp);
        return true;
    }
    public List<EmployeDto> getEmployesByDepartement(Departement departement) {
        List<Employe> employes = employeRepository.findByDepartement(departement);
        return employes.stream().map(employe-> {
            EmployeDto dto = mapperEmploye.toDto(employe);
            dto.setIdDep(employe.getDepartement() != null ? employe.getDepartement().getIdDep() : null);
            dto.setNomDep(employe.getDepartement()!= null ? employe.getDepartement().getNom() : null);
            return dto;
        }).toList();
    }


}
