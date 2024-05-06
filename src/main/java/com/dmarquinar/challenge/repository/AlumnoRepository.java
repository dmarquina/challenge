package com.dmarquinar.challenge.repository;

import com.dmarquinar.challenge.model.Alumno;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, Long> {
}