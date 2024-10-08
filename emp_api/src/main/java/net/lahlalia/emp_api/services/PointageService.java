package net.lahlalia.emp_api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lahlalia.emp_api.dtos.PointageDto;
import net.lahlalia.emp_api.entities.Pointage;
import net.lahlalia.emp_api.mappers.MapperPointage;
import net.lahlalia.emp_api.repositories.EmployeRepository;
import net.lahlalia.emp_api.repositories.PointageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointageService {

    private final PointageRepository pointageRepository;
    private final MapperPointage mapperPointage;
    private final EmployeRepository employeRepository;

    public List<PointageDto> getAllPointage(){
        return pointageRepository.findAll().stream().map(mapperPointage::toDto).toList();
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
