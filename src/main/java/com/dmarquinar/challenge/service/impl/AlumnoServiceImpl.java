package com.dmarquinar.challenge.service.impl;

import com.dmarquinar.challenge.dto.RequestAlumno;
import com.dmarquinar.challenge.exceptionhandler.AlumnoAlreadyExistsException;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.repository.AlumnoRepository;
import com.dmarquinar.challenge.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Flux<Alumno> findAlumnosActivos() {
        return alumnoRepository.findByEstadoActivo();
    }

    @Override
    public Mono<Void> createAlumno(RequestAlumno requestAlumno) {
        return alumnoRepository.findById(requestAlumno.getId())
                .flatMap(existingAlumno -> Mono.error(new AlumnoAlreadyExistsException()))
                .switchIfEmpty(Mono.defer(() -> {
                    Alumno alumno = new Alumno();
                    alumno.setId(requestAlumno.getId());
                    alumno.setNombre(requestAlumno.getNombre());
                    alumno.setApellido(requestAlumno.getApellido());
                    alumno.setEstado(requestAlumno.getEstado());
                    alumno.setEdad(requestAlumno.getEdad());
                    return alumnoRepository.saveAlumno(alumno).then();
                })).then();
    }
}
