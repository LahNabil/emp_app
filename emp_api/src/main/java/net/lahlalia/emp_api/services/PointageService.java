package net.lahlalia.emp_api.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lahlalia.emp_api.dtos.PageResponse;
import net.lahlalia.emp_api.dtos.PointageDto;
import net.lahlalia.emp_api.entities.Pointage;
import net.lahlalia.emp_api.mappers.MapperPointage;
import net.lahlalia.emp_api.repositories.EmployeRepository;
import net.lahlalia.emp_api.repositories.PointageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointageService {

    private final PointageRepository pointageRepository;
    private final MapperPointage mapperPointage;
    private final EmployeRepository employeRepository;

    public List<PointageDto> getAllPoints(){
        List<Pointage> pointageList = pointageRepository.findAll();
        List<PointageDto> pointageDtoList = pointageList.stream()
                .map(p->{
                    PointageDto dto = mapperPointage.toDto(p);
                    dto.setIdEmp(p.getEmploye() != null ? p.getEmploye().getIdEmp() : null);
                    dto.setNomEmp(p.getEmploye() != null ? p.getEmploye().getNom() : null);
                    return dto;
                }).toList();
        return pointageDtoList;
//        pointageRepository.findAll().stream().map(mapperPointage::toDto).toList();
    }
    public PageResponse<PointageDto> getAllPointage(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Pointage> pointages = pointageRepository.findAll(pageable);
        List<PointageDto> pointageDtos = pointages.stream()
                .map(pointage-> {
                    PointageDto dto = mapperPointage.toDto(pointage);
                    dto.setIdEmp(pointage.getEmploye() != null ? pointage.getEmploye().getIdEmp() : null);
                    dto.setNomEmp(pointage.getEmploye()!= null ? pointage.getEmploye().getNom() : null);
                    return dto;
                }).toList();
        return new PageResponse<>(
                pointageDtos,
                pointages.getNumber(),
                pointages.getSize(),
                pointages.getTotalElements(),
                pointages.getTotalPages(),
                pointages.isFirst(),
                pointages.isLast()
        );
    }
    public Integer updateArchivedStatus(Integer idPoi){
        Pointage poi = pointageRepository.findById(idPoi).orElseThrow(
                ()-> new EntityNotFoundException("No Pointage found" + idPoi)
        );
        poi.setArchived(!poi.isArchived());
        pointageRepository.save(poi);
        return idPoi;
    }
    public PointageDto savePointage(PointageDto dto){
        if (dto == null) {
            log.error("Pointage null");
            return null;
        }
        Pointage pointage = mapperPointage.toEntity(dto);
        if (dto.getIdEmp() != null) {
            employeRepository.findById(dto.getIdEmp()).ifPresent(pointage::setEmploye);
        }
        Pointage savedPointage = pointageRepository.save(pointage);
        return mapperPointage.toDto(savedPointage);
    }
    /*
    public PointageDto savePointage(PointageDto dto) {
        if (dto == null) {
            log.error("Pointage null");
            return null;
        }
        Pointage pointage = mapperPointage.toEntity(dto);
        if (dto.getIdEmp() != null) {
            employeRepository.findById(dto.getIdEmp()).ifPresent(pointage::setEmploye);
        }

        // Extraire la date sans l'heure pour comparer uniquement la journée
        LocalDateTime debutDeJournee = pointage.getDateEntre().toLocalDate().atStartOfDay();
        LocalDateTime finDeJournee = pointage.getDateEntre().toLocalDate().atTime(23, 59, 59);

        // Vérifier si un pointage existe déjà pour cet employé le même jour
        boolean existeDeja = pointageRepository.existsByEmployeAndDateEntreBetween(pointage.getEmploye(), debutDeJournee, finDeJournee);

        if (existeDeja) {
            throw new IllegalArgumentException("Un pointage pour cet employé existe déjà pour cette journée.");
        }

        // Sauvegarder le pointage si la vérification passe
        Pointage savedPointage = pointageRepository.save(pointage);
        return mapperPointage.toDto(savedPointage);
    }
     */

}
