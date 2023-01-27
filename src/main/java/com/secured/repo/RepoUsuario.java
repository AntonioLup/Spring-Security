package com.secured.repo;

import com.secured.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findOneByEmail(String email);
}
