package mx.edu.utez.hacktrece_api.controller.Roles;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.edu.utez.hacktrece_api.model.Roles.Roles;
import mx.edu.utez.hacktrece_api.services.Roles.RolesServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/roles")
@CrossOrigin(value = {"*"})
public class RolesController {
    private final RolesServices services;

    public RolesController(RolesServices services) {
        this.services = services;
    }

    @GetMapping("/")
    public ResponseEntity<Response<List<Roles>>> getAll() {
        return new ResponseEntity<>(this.services.getAll(), HttpStatus.OK);
    }


    @GetMapping("/{uid}")
    public ResponseEntity<Response<Roles>> getById(@PathVariable("uid") @NotNull String uid) {
        return new ResponseEntity<>(this.services.getById(uid), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Response<Roles>> insert(@RequestBody @Valid Roles rolesDto) {
        return new ResponseEntity<>(this.services.insert(rolesDto), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Response<Roles>> update(@RequestBody @Valid Roles rolesDto) {
        return new ResponseEntity<>(this.services.update(rolesDto), HttpStatus.OK);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("uid") @NotBlank String uid) {
        return new ResponseEntity<>(this.services.delete(uid), HttpStatus.OK);
    }
}
