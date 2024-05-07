package com.dmarquinar.challenge.controller;

import com.dmarquinar.challenge.dto.RequestAlumno;
import com.dmarquinar.challenge.exceptionhandler.AlumnoAlreadyExistsException;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping(value = "/activos")
    public Flux<Alumno> findAllAlumnosActivos() {
        return alumnoService.findAlumnosActivos();
    }

    @PostMapping("/")
    public Mono<Void> createAlumno(@Valid @RequestBody RequestAlumno requestAlumno) throws Exception {
        return alumnoService.createAlumno(requestAlumno).then();
    }
}

/*TODO:
    - Estudiar un poco sobre arquitectura hexagonal y clean architecture
    - Tests unitarios
    - Tests de integración
* */