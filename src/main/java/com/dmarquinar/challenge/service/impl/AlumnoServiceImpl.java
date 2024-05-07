package com.dmarquinar.challenge.service.impl;

import com.dmarquinar.challenge.dto.AlumnoResponse;
import com.dmarquinar.challenge.dto.AlumnoRequest;
import com.dmarquinar.challenge.exceptionhandler.AlumnoAlreadyExistsException;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.repository.AlumnoRepository;
import com.dmarquinar.challenge.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public Flux<AlumnoResponse> findAlumnosActivos() {
        return alumnoRepository.findByEstadoActivo()
                .map(alumno -> new AlumnoResponse(
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getEstado(),
                        alumno.getEdad()
                ));
    }

    @Override
    public Mono<Void> createAlumno(AlumnoRequest alumnoRequest) {
        return alumnoRepository.findById(alumnoRequest.getId())
                .flatMap(existingAlumno -> Mono.error(new AlumnoAlreadyExistsException()))
                .switchIfEmpty(Mono.defer(() -> {
                    Alumno alumno = new Alumno();
                    alumno.setId(alumnoRequest.getId());
                    alumno.setNombre(alumnoRequest.getNombre());
                    alumno.setApellido(alumnoRequest.getApellido());
                    alumno.setEstado(alumnoRequest.getEstado());
                    alumno.setEdad(alumnoRequest.getEdad());
                    return alumnoRepository.saveAlumno(alumno).then();
                })).then();
    }
}
