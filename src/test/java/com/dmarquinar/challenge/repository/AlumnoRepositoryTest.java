package com.dmarquinar.challenge.repository;

import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.model.Estado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

@DataR2dbcTest
public class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository repository;

    @Test
    public void testSaveAlumno() {
        Alumno alumno = new Alumno();
        alumno.setId(1L);
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno.setEstado(Estado.ACTIVO);
        alumno.setEdad(25);

        Mono<Void> saveResult = repository.saveAlumno(alumno);

        StepVerifier.create(saveResult)
                .verifyComplete();
    }

    @Test
    public void testFindByEstadoActivo() {
        Alumno alumno1 = new Alumno();
        alumno1.setId(10L);
        alumno1.setNombre("Juan");
        alumno1.setApellido("Perez");
        alumno1.setEstado(Estado.ACTIVO);
        alumno1.setEdad(25);

        Alumno alumno2 = new Alumno();
        alumno2.setId(20L);
        alumno2.setNombre("Maria");
        alumno2.setApellido("Gomez");
        alumno2.setEstado(Estado.INACTIVO);
        alumno2.setEdad(30);

        repository.saveAlumno(alumno1).block();
        repository.saveAlumno(alumno2).block();

        Flux<Alumno> alumnosActivos = repository.findByEstadoActivo();

        StepVerifier.create(alumnosActivos)
                .expectNextMatches(alumno -> alumno.getId() == 10L
                        && alumno.getNombre().equals("Juan")
                        && alumno.getApellido().equals("Perez")
                        && alumno.getEstado() == Estado.ACTIVO
                        && alumno.getEdad() == 25)
                .verifyComplete();
    }
}