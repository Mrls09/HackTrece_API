package mx.edu.utez.hacktrece_api.model.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignDto {
    @NotBlank
    @NotEmpty
    @Email(message = "El correo debe ser válido")
    private String email;
    @NotBlank
    @NotEmpty

    private String password;
}
