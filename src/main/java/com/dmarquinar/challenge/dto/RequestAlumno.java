package com.dmarquinar.challenge.dto;

import com.dmarquinar.challenge.model.Estado;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RequestAlumno {

    @NotNull(message = "id requerido")
    private Long id;

    @NotBlank(message = "Nombre requerido")
    private String nombre;

    @NotBlank(message = "Apellido requerido")
    private String apellido;

    @NotNull
    private Estado estado;

    @NotNull
    @Positive
    @Max(120)
    private int edad;


}
