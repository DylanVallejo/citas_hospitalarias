package com.hospital.gestorcitas.controller;


import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.PacienteDTO;
import com.hospital.gestorcitas.exceptions.CitaNotFoundException;
import com.hospital.gestorcitas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;


    @GetMapping
    public ResponseEntity<List<PacienteDTO>>  obtenerTodosLosPacientes(){
        try {
            List<PacienteDTO> pacientes = pacienteService.getAllPacientes();
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new CitaNotFoundException("Un error a ocurrido.");
    }


    @GetMapping("/{pacienteId}")
    public ResponseEntity<PacienteDTO> obtenerPacientePorId(@PathVariable Long pacienteId){
        try {
            Optional<PacienteDTO> pacienteDTOOptional = pacienteService.getPacienteById(pacienteId);
            return new ResponseEntity<>(pacienteDTOOptional.get(), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new CitaNotFoundException("Un error a ocurrido.");
    }


    @PostMapping("/create")
    public ResponseEntity<PacienteDTO> crearPaciente(@RequestBody PacienteDTO pacienteDTO){
        try {
            PacienteDTO pacienteNuevo = pacienteService.createPaciente(pacienteDTO);
            return new ResponseEntity<>(pacienteNuevo, HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new CitaNotFoundException("Un error a ocurrido.");
    }


    @PutMapping("/actualizar/{pacienteId}")
    public ResponseEntity<PacienteDTO> actualizarPaciente(@PathVariable Long pacienteId, @RequestBody PacienteDTO pacienteDTO){
        try {
            PacienteDTO pacienteActualizado = pacienteService.updatePaciente(pacienteId,pacienteDTO);
            if (pacienteActualizado != null){
                return  new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new CitaNotFoundException("Un error a ocurrido.");
    }

    @DeleteMapping("/eliminar/{pacienteId}")
    public ResponseEntity<Void> eliminarPacientePorId(@PathVariable Long pacienteId){
        try {
            pacienteService.deletePaciente(pacienteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new CitaNotFoundException("Un error a ocurrido.");
    }


    @GetMapping("/citas/{pacienteId}")
    public ResponseEntity<Collection<CitaDTO>> obtenerCitasPorPaciente(@PathVariable Long pacienteId){
        try {
            Collection<CitaDTO> citasPaciente = pacienteService.getCitasByPacienteId(pacienteId);
            if (citasPaciente != null){
                return new ResponseEntity<>(citasPaciente, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        throw new CitaNotFoundException("Un error a ocurrido.");
    }




}
