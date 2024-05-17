package mx.edu.utez.hacktrece_api.model.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {


    Roles findByName(String name);

    Roles findByIdRole(String idRole);


}
