package com.dmarquinar.challenge.controller;

import com.dmarquinar.challenge.dto.AlumnoRequest;
import com.dmarquinar.challenge.dto.AlumnoResponse;
import com.dmarquinar.challenge.exceptionhandler.AlumnoAlreadyExistsException;
import com.dmarquinar.challenge.model.Alumno;
import com.dmarquinar.challenge.model.Estado;
import com.dmarquinar.challenge.service.AlumnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlumnoControllerTest {
    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    // List Active Alumnos Tests
    @Test
    public void testFindAllAlumnosActivos() {
        AlumnoResponse alumno1 = new AlumnoResponse(1L, "Juan", "Perez", Estado.ACTIVO, 25);
        AlumnoResponse alumno2 = new AlumnoResponse(2L, "Maria", "Gomez", Estado.ACTIVO, 30);

        when(alumnoService.findAlumnosActivos()).thenReturn(Flux.just(alumno1, alumno2));

        Flux<AlumnoResponse> result = alumnoController.findAllAlumnosActivos();
        StepVerifier.create(result)
                .expectNext(alumno1, alumno2)
                .verifyComplete();
    }


    // Create Alumno Tests
    @Test
    public void testCreateAlumno_Success() throws Exception {
        when(alumnoService.createAlumno(any(AlumnoRequest.class))).thenReturn(Mono.empty());

        Mono<Void> result = alumnoController.createAlumno(new AlumnoRequest());
        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void testCreateAlumno_AlreadyExists() throws Exception {
        when(alumnoService.createAlumno(any(AlumnoRequest.class)))
                .thenReturn(Mono.error(new AlumnoAlreadyExistsException()));

        Mono<Void> result = alumnoController.createAlumno(new AlumnoRequest());

        StepVerifier.create(result)
                .expectError(ResponseStatusException.class)
                .verify();
    }

    /*
    @Test
    public void testCreateAlumno_WithInvalidRequest() throws Exception {

    }*/
}