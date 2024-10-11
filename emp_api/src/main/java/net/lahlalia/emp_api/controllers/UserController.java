package net.lahlalia.emp_api.controllers;

import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.UserDto;
import net.lahlalia.emp_api.services.AuthenticationService;
import net.lahlalia.emp_api.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/name")
    public String getUserDetails() {
        return "username: " + userService.getName();
    }

    @GetMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserName() {
        String username = userService.getUserName();
        return ResponseEntity.ok(username);
    }

    @GetMapping("/details")
    public UserDto getDetails(){
        return userService.getAuthenticatedUser();
    }
}
