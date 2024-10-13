package net.lahlalia.emp_api.mappers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.EmployeDto;
import net.lahlalia.emp_api.entities.Employe;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperEmploye {

    private final ModelMapper mapper;

    public Employe toEntity(EmployeDto dto){
        return mapper.map(dto, Employe.class);

    }
    public EmployeDto toDto(Employe employe){
        return mapper.map(employe, EmployeDto.class);
    }
}
