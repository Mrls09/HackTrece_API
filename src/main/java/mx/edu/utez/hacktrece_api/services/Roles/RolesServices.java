package mx.edu.utez.hacktrece_api.services.Roles;

import mx.edu.utez.hacktrece_api.model.Roles.Roles;
import mx.edu.utez.hacktrece_api.model.Roles.RolesRepository;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolesServices {
    private final RolesRepository repository;

    @Autowired
    public RolesServices(RolesRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Response<List<Roles>> getAll() {
        return new Response<>(this.repository.findAll(), false, 200, "OK");
    }

    @Transactional(readOnly = true)
    public Response<Roles> getById(String id) {
        Optional<Roles> entityGet = this.repository.findById(id);
        return entityGet.map(roles -> new Response<>(roles, false, 200, "OK")).orElseGet(() -> new Response<>(null, true, 400, "No encontrado"));
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Response<Roles> insert(Roles roles) {
        return new Response<>(this.repository.save(roles), false, 200, "OK");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Roles> update(Roles roles) {
        Optional<Roles> entityUpdate = this.repository.findById(roles.getIdRole());
        if (entityUpdate.isPresent()) {
            return new Response<>(this.repository.saveAndFlush(roles), false, 200, "OK");
        }
        return new Response<>(null, true, 400, "No encontrado");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> delete(String id) {
        Optional<Roles> entityDelete = this.repository.findById(id);
        if (entityDelete.isPresent()) {
            Roles roles = entityDelete.get();
            this.repository.delete(roles);
            return new Response<>(true, false, 200, "OK");
        }
        return new Response<>(false, true, 400, "No encontrado");
    }
}
