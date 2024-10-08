package net.lahlalia.emp_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    @Email(message = "email is not correct")
    private String email;

    @NotEmpty(message = "username is mandatory")
    @NotBlank(message = "username is mandatory")
    private String username;

    @NotEmpty(message = "phone is mandatory")
    @NotBlank(message = "phone is mandatory")
    private String phone;

    @NotEmpty(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min= 8, message = "password should be 8 characters minimum")
    private String password;
}
