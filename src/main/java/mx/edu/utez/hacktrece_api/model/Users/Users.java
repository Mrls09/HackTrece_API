package mx.edu.utez.hacktrece_api.model.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.hacktrece_api.model.Roles.Roles;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_user")
    @UuidGenerator
    private String idUser;
    private String name;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Roles> roles = new HashSet<>();

}
