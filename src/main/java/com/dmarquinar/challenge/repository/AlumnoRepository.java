package com.dmarquinar.challenge.repository;

import com.dmarquinar.challenge.model.Alumno;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, Long> {

    @Query("INSERT INTO alumno (id, nombre, apellido, estado, edad) VALUES (:#{#alumno.id}, :#{#alumno.nombre}, :#{#alumno.apellido}, :#{#alumno.estado}, :#{#alumno.edad})")
    Mono<Void> saveAlumno(@Param("alumno") Alumno alumno);

    @Query("SELECT * FROM alumno WHERE estado = 'ACTIVO'")
    Flux<Alumno> findByEstadoActivo();
}