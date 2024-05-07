package com.dmarquinar.challenge.service;

import com.dmarquinar.challenge.dto.AlumnoResponse;
import com.dmarquinar.challenge.dto.AlumnoRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {
    Flux<AlumnoResponse> findAlumnosActivos();

    Mono<Void> createAlumno(AlumnoRequest alumnoRequest) throws Exception;
}
