package net.lahlalia.emp_api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.lahlalia.emp_api.dtos.UserDto;
import net.lahlalia.emp_api.services.AuthenticationService;
import net.lahlalia.emp_api.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserDto> getDetails(){
        UserDto userDto =userService.getAuthenticatedUser();
        return ResponseEntity.ok(userDto);
    }
    @PutMapping("/")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

}
