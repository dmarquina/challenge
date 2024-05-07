package com.dmarquinar.challenge.controller;

import com.dmarquinar.challenge.dto.AlumnoRequest;
import com.dmarquinar.challenge.dto.AlumnoResponse;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.service.AlumnoService;
import com.dmarquinar.challenge.service.impl.AlumnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping(value = "/activos")
    public Flux<AlumnoResponse> findAllAlumnosActivos() {
        return alumnoService.findAlumnosActivos();
    }

    @PostMapping("/")
    public Mono<Void> createAlumno(@Valid @RequestBody AlumnoRequest alumnoRequest) throws Exception {
        return alumnoService.createAlumno(alumnoRequest).then();
    }
}