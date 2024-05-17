package mx.edu.utez.hacktrece_api.model.Users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersTokenDto {
    @NotNull
    private Users users;

    @NotBlank
    private String token;


}
