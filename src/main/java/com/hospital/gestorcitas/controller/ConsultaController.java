package com.hospital.gestorcitas.controller;


import com.hospital.gestorcitas.dto.ConsultaDTO;
import com.hospital.gestorcitas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> obtenerConsultas() {
        try {
            List<ConsultaDTO> consultas = consultaService.getAllConsultas();
            if (consultas != null) {
                return new ResponseEntity<>(consultas, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/{consultaId}")
    public ResponseEntity<ConsultaDTO> obtenerConsultaPorId(@PathVariable Long consultaId) {
        try {
            Optional<ConsultaDTO> consultaDTO = consultaService.getConsultaById(consultaId);
            return consultaDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear/{citaId}")
    public ResponseEntity<ConsultaDTO> crearConsulta(@RequestBody ConsultaDTO consultaDTO, @PathVariable Long citaId) {
        try {
            ConsultaDTO consultaNueva = consultaService.createConsulta(citaId, consultaDTO);
            return new ResponseEntity<>(consultaNueva, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/actualizar/{citaId}")
    public ResponseEntity<ConsultaDTO> actualizarConsulta(@RequestBody ConsultaDTO consultaDTO, @PathVariable Long citaId) throws ParseException {
        try {
            ConsultaDTO consultaActualizada = consultaService.updateConsulta(citaId, consultaDTO);
            if (consultaActualizada != null) {
                return new ResponseEntity<>(consultaActualizada, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //   TODO NO CONTIENE IMPLEMENTACION
    @DeleteMapping("/{consultaId}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Long consultaId) {
        try {
            consultaService.deleteConsulta(consultaId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/informe")
    public ResponseEntity<List<ConsultaDTO>> listarConsultaPorInforme(@RequestParam String searchTerm) {
        try {
            List<ConsultaDTO> consultaConInforme = consultaService.getConsultasByInformeContaining(searchTerm);
            return new ResponseEntity<>(consultaConInforme, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<ConsultaDTO>> obtenerConsultaPorCitaId(@PathVariable Long citaId) {
        try {
            List<ConsultaDTO> consultaPorCitaId = consultaService.getConsultasByCitaId(citaId);
            return new ResponseEntity<>(consultaPorCitaId, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
