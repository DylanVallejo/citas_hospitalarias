package com.hospital.gestorcitas.controller;


import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.MedicoDTO;
import com.hospital.gestorcitas.mapper.MedicoMapper;
import com.hospital.gestorcitas.model.Medico;
import com.hospital.gestorcitas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/medico")
public class MedicoController {


    @Autowired
    MedicoService medicoService;

    @Autowired
    MedicoMapper medicoMapper;


    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarMedicos(){
        List<MedicoDTO> medicos = medicoService.getAllMedicos();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable  Long id){
        Optional<MedicoDTO> medico = medicoService.getMedicoById(id);
        return new ResponseEntity<>(medico.get(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public  ResponseEntity<MedicoDTO> createMedico(@RequestBody MedicoDTO medicoDTO){
        MedicoDTO medicoToSave = medicoService.createMedico(medicoDTO);
        return new ResponseEntity<>(medicoToSave, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MedicoDTO> updateMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO){
        MedicoDTO medicoUpdated = medicoService.updateMedico(id, medicoDTO);
        return new ResponseEntity<>(medicoUpdated, HttpStatus.OK);
    }

    @GetMapping("/citas/{medicoId}")
    public  ResponseEntity<List<CitaDTO>> obtenerCitasPorMedicoId(@PathVariable Long medicoId){
//        List<CitaDTO> citasPormedico = medicoService.getCitasByMedicoId(medicoId);

        return new ResponseEntity<>(citasPormedico, HttpStatus.OK);
    }


}
