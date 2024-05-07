package com.dmarquinar.challenge.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlumnoAlreadyExistsException extends ResponseStatusException {

    public AlumnoAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Error: No se pudo hacer la grabación, este Alumno ya existe.");
    }


}