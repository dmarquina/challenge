package com.dmarquinar.challenge.service.impl;

import com.dmarquinar.challenge.dto.RequestAlumno;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.repository.AlumnoRepository;
import com.dmarquinar.challenge.service.AlumnoService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service("alumnoService")
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Flux<Alumno> findAlumnos() {
        return alumnoRepository.findAll();
    }

    @Override
    public Mono<Alumno> createAlumno(RequestAlumno requestAlumno) throws Exception {
        Alumno alumno = new Alumno();
        alumno.setNombre(requestAlumno.getNombre());
        alumno.setApellido(requestAlumno.getApellido());
        alumno.setEstado(requestAlumno.isActivo());
        alumno.setEdad(requestAlumno.getEdad());
        try{
            return alumnoRepository.save(alumno);
        } catch (Exception e) {
            throw new Exception("Error al crear alumno");
        }


    }
}
