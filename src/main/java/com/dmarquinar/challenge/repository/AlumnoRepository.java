package com.dmarquinar.challenge.repository;

import com.dmarquinar.challenge.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}