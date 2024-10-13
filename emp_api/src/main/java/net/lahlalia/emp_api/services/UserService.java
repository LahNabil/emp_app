package net.lahlalia.emp_api.services;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.UserDto;
import net.lahlalia.emp_api.entities.User;
import net.lahlalia.emp_api.mappers.MapperUser;
import net.lahlalia.emp_api.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MapperUser mapperUser;
    private final UserRepository userRepository;

    public UserDto getAuthenticatedUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof User){
            User user = (User) authentication.getPrincipal();
            return mapperUser.toDto(user);

        }

        return null;
    }
    public String getName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof User){
            User user = (User) authentication.getPrincipal();
            return user.getName();
        }
        return null;
    }
    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof User){
            User user = (User) authentication.getPrincipal();
            return user.fullName();
        }
        return null;
    }
    public UserDto updateUser(UserDto updatedUserDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User currentUser = (User) authentication.getPrincipal();

            // Optionally fetch the user from the repository to ensure it's up-to-date
            User user = userRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setName(updatedUserDto.getName());
            user.setEmail(updatedUserDto.getUsername());
            user.setPhone(updatedUserDto.getPhone());
            user.setUsername(updatedUserDto.getEmail());

            user = userRepository.save(user);

            return mapperUser.toDto(user);
        }

        throw new RuntimeException("User is not authenticated");
    }

}
