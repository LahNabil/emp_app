package net.lahlalia.emp_api.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    @NotEmpty(message = "103")
    @NotNull(message = "103")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Le numéro de téléphone doit commencer par 0 et contenir exactement 10 chiffres.")
    private String phone;

    @NotEmpty(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min= 8, message = "password should be 8 characters minimum")
    private String password;
}
