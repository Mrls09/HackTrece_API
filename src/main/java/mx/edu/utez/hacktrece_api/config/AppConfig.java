package mx.edu.utez.hacktrece_api.config;

import jakarta.annotation.PostConstruct;
import mx.edu.utez.hacktrece_api.model.Roles.Roles;
import mx.edu.utez.hacktrece_api.model.Roles.RolesRepository;
import mx.edu.utez.hacktrece_api.model.Users.Users;
import mx.edu.utez.hacktrece_api.model.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Configuration
public class AppConfig {

    private final RolesRepository rolesRepository;
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppConfig(RolesRepository rolesRepository, UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.rolesRepository = rolesRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (rolesRepository.count() != 0) return;

        saveRole("ADMIN");
        saveRole("CLIENTE");
    }

    private void saveRole(String roleName) {
        Roles roles = new Roles(null,roleName,null);
        rolesRepository.save(roles);
    }

    private void initUsers() {
        if (usersRepository.count() != 0) return;

        rolesRepository.findAll().forEach(this::processRole);
    }

    private void processRole(Roles roles) {
        String roleName = roles.getName();
        switch (roleName) {
            case "ADMIN" ->
                    saveUser(roles, "Cristian", "redalphasiete@gmail.com", "admin");
            case "CLIENTE" ->
                    saveUser(roles, "Juan", "juancamaney@yopmail.com", "cliente");
        }
    }

    private void saveUser(Roles roles, String name, String email, String password) {
        Set<Roles> rolesUser = new HashSet<>();
        rolesUser.add(roles);
        Users users = new Users(null, name, email, password, rolesUser);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
    }

}
