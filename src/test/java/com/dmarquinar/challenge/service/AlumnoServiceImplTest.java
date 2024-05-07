package com.dmarquinar.challenge.service;

import com.dmarquinar.challenge.dto.AlumnoRequest;
import com.dmarquinar.challenge.dto.AlumnoResponse;
import com.dmarquinar.challenge.exceptionhandler.AlumnoAlreadyExistsException;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.model.Estado;
import com.dmarquinar.challenge.repository.AlumnoRepository;
import com.dmarquinar.challenge.service.impl.AlumnoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceImplTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @Test
    public void testFindAlumnosActivos() {
        Alumno alumno1 = new Alumno();
        alumno1.setId(1L);
        alumno1.setNombre("Juan");
        alumno1.setApellido("Perez");
        alumno1.setEstado(Estado.ACTIVO);
        alumno1.setEdad(25);

        Alumno alumno2 = new Alumno();
        alumno2.setId(2L);
        alumno2.setNombre("Maria");
        alumno2.setApellido("Gomez");
        alumno2.setEstado(Estado.ACTIVO);
        alumno2.setEdad(30);

        List<Alumno> alumnosFromDb = List.of(alumno1, alumno2);

        AlumnoResponse expectedAlumnoResponse1 = new AlumnoResponse();
        expectedAlumnoResponse1.setId(1L);
        expectedAlumnoResponse1.setNombre("Juan");
        expectedAlumnoResponse1.setApellido("Perez");
        expectedAlumnoResponse1.setEstado(Estado.ACTIVO);
        expectedAlumnoResponse1.setEdad(25);

        AlumnoResponse expectedAlumnoResponse2 = new AlumnoResponse();
        expectedAlumnoResponse2.setId(2L);
        expectedAlumnoResponse2.setNombre("Maria");
        expectedAlumnoResponse2.setApellido("Gomez");
        expectedAlumnoResponse2.setEstado(Estado.ACTIVO);
        expectedAlumnoResponse2.setEdad(30);

        when(alumnoRepository.findByEstadoActivo()).thenReturn(Flux.fromIterable(alumnosFromDb));

        Flux<AlumnoResponse> result = alumnoService.findAlumnosActivos();

        StepVerifier.create(result)
                .expectNext(expectedAlumnoResponse1)
                .expectNext(expectedAlumnoResponse2)
                .verifyComplete();
    }

    @Test
    public void testCreateAlumno_Success() throws Exception {
        AlumnoRequest alumnoRequest = new AlumnoRequest();
        alumnoRequest.setId(1L);
        alumnoRequest.setNombre("Juan");
        alumnoRequest.setApellido("Perez");
        alumnoRequest.setEstado(Estado.ACTIVO);
        alumnoRequest.setEdad(20);

        Alumno expectedAlumno = new Alumno();
        expectedAlumno.setId(1L);
        expectedAlumno.setNombre("Juan");
        expectedAlumno.setApellido("Perez");
        expectedAlumno.setEstado(Estado.ACTIVO);
        expectedAlumno.setEdad(20);

        when(alumnoRepository.findById(1L)).thenReturn(Mono.empty());
        when(alumnoRepository.saveAlumno(any(Alumno.class))).thenReturn(Mono.empty());

        Mono<Void> result = alumnoService.createAlumno(alumnoRequest);

        StepVerifier.create(result)
                .verifyComplete();

    }

    @Test
    public void testCreateAlumno_AlreadyExists() {
        AlumnoRequest alumnoRequest = new AlumnoRequest();
        alumnoRequest.setId(1L);
        alumnoRequest.setNombre("Juan");
        alumnoRequest.setApellido("Perez");
        alumnoRequest.setEstado(Estado.ACTIVO);
        alumnoRequest.setEdad(20);

        when(alumnoRepository.findById(1L)).thenReturn(Mono.just(new Alumno()));

        StepVerifier.create(alumnoService.createAlumno(alumnoRequest))
                .expectError(AlumnoAlreadyExistsException.class)
                .verify();
    }
}