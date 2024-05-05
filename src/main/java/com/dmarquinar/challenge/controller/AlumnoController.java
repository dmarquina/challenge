package com.dmarquinar.challenge.controller;

import com.dmarquinar.challenge.service.AlumnoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoService alumnoService;


    @ApiOperation(value = "Listar alumnos", notes = "Servicio para listar las alumnos")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Alumnos listadas correctamente"),
            @ApiResponse(code = 400, message = "Solicitud inválida"),
            @ApiResponse(code = 500, message = "Error en el servidor") })
    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PlantAdminResponse>> findAllPlants() {
        return ResponseEntity.ok(alumnoService.findAllPlants()
                .stream()
                .map(PlantAdminResponse::new)
                .collect(Collectors.toList()));
    }
}
