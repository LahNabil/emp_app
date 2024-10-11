package net.lahlalia.emp_api.services;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.UserDto;
import net.lahlalia.emp_api.entities.User;
import net.lahlalia.emp_api.mappers.MapperUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MapperUser mapperUser;

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
//    public String getUsername() {
//        User user = getAuthenticatedUser();
//        return (user != null) ? user.getUsername() : null;  // This returns the email as per your User class
//    }

//    public String getUsername(){
//        UserDetails userDetails = getAuthenticatedUser();
//        return (userDetails != null) ? userDetails.getUsername() : null;
//    }
}
