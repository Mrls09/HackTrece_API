package mx.edu.utez.hacktrece_api.services.auth;

import mx.edu.utez.hacktrece_api.model.Users.Users;
import mx.edu.utez.hacktrece_api.model.Users.UsersTokenDto;
import mx.edu.utez.hacktrece_api.security.jwt.JwtProvider;
import mx.edu.utez.hacktrece_api.services.Users.UsersServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@Transactional
public class AuthService {

    private final Logger logger = Logger.getLogger(AuthService.class.getName());

    private final UsersServices service;

    private final AuthenticationManager manager;

    private final JwtProvider provider;

    @Autowired
    public AuthService(UsersServices service, AuthenticationManager manager, JwtProvider provider) {
        this.service = service;
        this.manager = manager;
        this.provider = provider;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Response<UsersTokenDto>> login(String username, String password) {
        try {
            Users foundUser = service.getByEmail(username);
            if (foundUser == null)
                return new ResponseEntity<>(new Response<>(null, true, 404, "Usuario no encontrado para login"), HttpStatus.NOT_FOUND);

            Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = provider.generateToken(auth);
            UsersTokenDto data = new UsersTokenDto(foundUser, token);
            return new ResponseEntity<>(new Response<>(data, false, 200, "OK"), HttpStatus.OK);
        } catch (Exception e) {
            this.logger.severe(e.getMessage());
            String message = "Credenciales incorrectas";
            if (e instanceof DisabledException) message = "Usuario inactivo";
            return new ResponseEntity<>(new Response<>(null, true, 401, message), HttpStatus.BAD_REQUEST);
        }
    }
}
