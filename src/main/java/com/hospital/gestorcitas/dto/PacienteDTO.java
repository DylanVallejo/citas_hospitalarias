package com.hospital.gestorcitas.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PacienteDTO {

    private long id;
    private String nombre;
    private Date fechaNacimiento;
    private boolean enfermedad;


}
