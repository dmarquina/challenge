package com.dmarquinar.challenge.service;

import com.dmarquinar.challenge.dto.RequestAlumno;
import com.dmarquinar.challenge.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {
    Flux<Alumno> findAlumnosActivos();

    Mono<Void> createAlumno(RequestAlumno requestAlumno) throws Exception;
}
