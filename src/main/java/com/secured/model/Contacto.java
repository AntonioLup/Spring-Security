package com.secured.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Column(name = "fecha")
    private LocalDate fechaNacimiento;

    private String telefono;
    private String email;

}
