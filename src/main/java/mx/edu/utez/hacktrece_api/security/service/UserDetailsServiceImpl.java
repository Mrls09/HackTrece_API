package mx.edu.utez.hacktrece_api.security.service;

import mx.edu.utez.hacktrece_api.model.Users.Users;
import mx.edu.utez.hacktrece_api.model.Users.UsersRepository;
import mx.edu.utez.hacktrece_api.security.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users foundUser = this.repository.findByEmail(username);
        if (foundUser != null) return UserDetailsImpl.build(foundUser);
        throw new UsernameNotFoundException("UserNotFound");
    }
}
