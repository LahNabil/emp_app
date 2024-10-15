package net.lahlalia.emp_api.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    @NotEmpty(message = "Mot de pass Actuel requis")
    @NotNull(message = "Mot de pass Actuel requis")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String currentPassword;

    @NotEmpty(message = "Nouveau Mot de pass Actuel requis")
    @NotNull(message = "Nouveau Mot de pass Actuel requis")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String newPassword;

    @NotEmpty(message = "Mot de pass de confirmation Actuel requis")
    @NotNull(message = "Mot de pass de confirmation Actuel requis")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String confirmationPassword;
}
