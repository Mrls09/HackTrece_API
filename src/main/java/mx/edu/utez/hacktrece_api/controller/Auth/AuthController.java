package mx.edu.utez.hacktrece_api.controller.Auth;


import jakarta.validation.Valid;
import mx.edu.utez.hacktrece_api.model.Users.SignDto;
import mx.edu.utez.hacktrece_api.model.Users.UsersTokenDto;
import mx.edu.utez.hacktrece_api.services.auth.AuthService;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/auth")
@CrossOrigin(value = {"*"})
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Response<UsersTokenDto>> login(@RequestBody @Valid SignDto dto) {
        return service.login(dto.getEmail(), dto.getPassword());
    }
}
