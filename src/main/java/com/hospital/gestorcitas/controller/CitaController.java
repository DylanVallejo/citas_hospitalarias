package com.hospital.gestorcitas.controller;


import ch.qos.logback.core.joran.spi.ElementSelector;
import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.mapper.CitaMapper;
import com.hospital.gestorcitas.mapper.MedicoMapper;
import com.hospital.gestorcitas.mapper.PacienteMapper;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.StatusCita;
import com.hospital.gestorcitas.service.CitaService;
import com.hospital.gestorcitas.service.MedicoService;
import com.hospital.gestorcitas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaMapper citaMapper;


    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarCitas(){
        List<CitaDTO> citas = citaService.getAllCitas();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> listarPorId(@PathVariable Long id){
        Optional<CitaDTO> citaDTOOptional = citaService.getCitaById(id);
        return  citaDTOOptional.map(cita-> new ResponseEntity<>(cita, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{idPaciente}/{idMedico}")
    public ResponseEntity<CitaDTO> guardarCita(@RequestBody CitaDTO citaDTO, @PathVariable Long idPaciente, @PathVariable Long idMedico) throws Exception {
        Cita newCita = citaService.createCita(citaDTO, idPaciente, idMedico);
        if (newCita == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CitaDTO newCitaDto = citaMapper.toDto(newCita);
        return  new ResponseEntity<>(citaDTO, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizarCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) throws ParseException {
        CitaDTO citaUpdate = citaService.updateCita(id,citaDTO);
        if (citaUpdate != null ){
            return ResponseEntity.ok(citaUpdate);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id){
        citaService.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/paciente/{pacienteId}")
    public List<CitaDTO> listarCitasPorPacienteId(@PathVariable Long pacienteId){
        return citaService.getCitasByPacienteId(pacienteId);
    }

    @GetMapping("/medico/{medicoId}")
    public List<CitaDTO> listarCitasPorMedicoId(@PathVariable Long medicoId){
        return citaService.getCitasByMedicoId(medicoId);
    }


    @GetMapping("/status/{statusCita}")
    public List<CitaDTO> listarCitasPorStatus(@PathVariable StatusCita statusCita){
        return citaService.getCitasByStatusCita(statusCita);
    }





}
