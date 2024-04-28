package com.hospital.gestorcitas.controller;


import com.hospital.gestorcitas.service.CitaService;
import com.hospital.gestorcitas.service.MedicoService;
import com.hospital.gestorcitas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;


    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;




}
