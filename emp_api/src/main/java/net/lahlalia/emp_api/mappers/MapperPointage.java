package net.lahlalia.emp_api.mappers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.PointageDto;
import net.lahlalia.emp_api.entities.Pointage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperPointage {

    private final ModelMapper mapper;

    public Pointage toEntity(PointageDto dto){
        return mapper.map(dto, Pointage.class);

    }
    public PointageDto toDto(Pointage pointage){
        return mapper.map(pointage, PointageDto.class);
    }
}
