package mx.edu.utez.hacktrece_api.services.Users;

import mx.edu.utez.hacktrece_api.model.Roles.RolesRepository;
import mx.edu.utez.hacktrece_api.model.Users.Users;
import mx.edu.utez.hacktrece_api.model.Users.UsersRepository;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersServices {
    private final UsersRepository repository;

    private final RolesRepository rolesRepository;


    private final PasswordEncoder passwordEncoder;



    public UsersServices(UsersRepository repository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Response<List<Users>> getAll() {
        return new Response<>(this.repository.findAll(), false, 200, "OK");
    }

    @Transactional(readOnly = true)
    public Response<Optional<Users>> getById(String id) {
        Optional<Users> entityGet = this.repository.findById(id);
        if (entityGet.isPresent()) {
            return new Response<>(entityGet, false, 200, "OK");
        }
        return new Response<>(null, true, 400, "No encontrado");
    }

    @Transactional(readOnly = true)
    public Users getByEmail(String email) {
        Users entityGet = this.repository.findByEmail(email);
        return entityGet;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Users> insert(Users users) {
        Users existe = this.repository.findByEmail(users.getEmail());
        if (existe != null) {
            return new Response<>(null, true, 400, "Correo ya registrado");
        }
        users.setPassword(this.passwordEncoder.encode(users.getPassword()));
        return new Response<>(this.repository.save(users), false, 200, "OK");
    }



    @Transactional(rollbackFor = {SQLException.class})
    public Response<Users> update(Users users) {

        Users usuario = this.repository.findByEmail(users.getEmail());
        if (usuario != null) {
            this.repository.saveAndFlush(users);
            return new Response<>(null, true, 400, "Correo ya registrado");
        }
        return new Response<>(null, true, 400, "No encontrado para actualizar");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> delete(String id) {
        Optional<Users> usuarios = this.repository.findById(id);
        if (usuarios.isPresent()) {
            this.repository.delete(usuarios.get());
            return new Response<>(true, false, 200, "Eliminado correctamente");
        }
        return new Response<>(null, true, 400, "No encontrado para eliminar");
    }

    public long count() {
        return this.repository.count();
    }


}
