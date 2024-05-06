package com.dmarquinar.challenge.controller;

import com.dmarquinar.challenge.dto.RequestAlumno;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<Alumno> findAllAlumnos() {
        return alumnoService.findAlumnos();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Alumno> createAlumno(@RequestBody RequestAlumno requestAlumno) throws Exception {
        return alumnoService.createAlumno(requestAlumno);
    }
}
