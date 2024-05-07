package com.dmarquinar.challenge.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "alumno")
public class Alumno {

    @Id
    private Long id;
    private String nombre;
    private String apellido;
    private Estado estado;
    private int edad;

}
