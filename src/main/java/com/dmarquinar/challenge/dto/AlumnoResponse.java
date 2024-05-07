package com.dmarquinar.challenge.dto;

import com.dmarquinar.challenge.model.Estado;
import lombok.Data;

@Data
public class AlumnoResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private Estado estado;
    private int edad;

    public AlumnoResponse(){}
    public AlumnoResponse(Long id, String nombre, String apellido, Estado estado, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.edad = edad;
    }
}