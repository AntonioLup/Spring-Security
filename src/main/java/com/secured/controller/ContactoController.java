package com.secured.controller;

import com.secured.model.Contacto;
import com.secured.repo.RepoContacto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/contactos")
@AllArgsConstructor
public class ContactoController {
    @Autowired
    private final RepoContacto repoContacto;

    @GetMapping
    public List<Contacto> listContacts(){
        return repoContacto.findAll();
    }
}
