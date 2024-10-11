package net.lahlalia.emp_api.mappers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.UserDto;
import net.lahlalia.emp_api.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MapperUser {

    private final ModelMapper mapper;

    public User toEntity(UserDto dto){
        return mapper.map(dto, User.class);

    }
    public UserDto toDto(User user){
        return mapper.map(user, UserDto.class);
    }

}
