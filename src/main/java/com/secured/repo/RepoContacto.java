package com.secured.repo;

import com.secured.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoContacto extends JpaRepository<Contacto, Long> {
}
