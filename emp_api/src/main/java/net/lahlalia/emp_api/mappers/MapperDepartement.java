package net.lahlalia.emp_api.mappers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.DepartementDto;
import net.lahlalia.emp_api.entities.Departement;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperDepartement {

    private final ModelMapper mapper;

    public Departement toEntity(DepartementDto dto){
        return mapper.map(dto, Departement.class);

    }
    public DepartementDto toDto(Departement departement){
        return mapper.map(departement, DepartementDto.class);


    }

}
