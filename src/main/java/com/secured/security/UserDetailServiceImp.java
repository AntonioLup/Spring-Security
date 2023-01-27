package com.secured.security;

import com.secured.model.Usuario;
import com.secured.repo.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    private RepoUsuario repoUsuario;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repoUsuario
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
        return new UserDetailImp(usuario);

    }
}
