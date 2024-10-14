package net.lahlalia.emp_api.services;

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

}
