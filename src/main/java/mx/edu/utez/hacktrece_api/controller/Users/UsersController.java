package mx.edu.utez.hacktrece_api.controller.Users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import mx.edu.utez.hacktrece_api.model.Users.Users;
import mx.edu.utez.hacktrece_api.services.Users.UsersServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${apiPrefix}/usuarios")
@CrossOrigin(value = {"*"})
public class UsersController {
    private final UsersServices services;

    public UsersController(UsersServices services) {
        this.services = services;
    }

    @GetMapping("/")
    public ResponseEntity<Response<List<Users>>> getAll() {
        return new ResponseEntity<>(this.services.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Response<Optional<Users>>> getById(@PathVariable("uid") @NotBlank String uid) {
        return new ResponseEntity<>(this.services.getById(uid), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Response<Users>> insert(@RequestBody @Valid Users user ) {
        return new ResponseEntity<>(this.services.insert(user), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Response<Users>> update(@RequestBody @Valid Users user) {
        return new ResponseEntity<>(this.services.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("uid") @NotBlank String uid) {
        return new ResponseEntity<>(this.services.delete(uid), HttpStatus.OK);
    }
}
