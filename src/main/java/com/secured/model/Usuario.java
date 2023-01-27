package com.secured.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUsuaio")
    private Long id;
    private String nombre;
    private String email;
    private String password;

}
