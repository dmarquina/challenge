package com.dmarquinar.challenge.service;

import com.dmarquinar.challenge.dto.RequestAlumno;
import com.dmarquinar.challenge.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AlumnoService {
    Flux<Alumno> findAlumnos();

    Mono<Alumno> createAlumno(RequestAlumno requestAlumno) throws Exception;
}
