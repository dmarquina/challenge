package com.dmarquinar.challenge.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private boolean estado;
    private int edad;
}
