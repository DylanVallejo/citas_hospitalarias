package com.hospital.gestorcitas.dto;


import lombok.Data;

@Data
public class CitaDTO {


    private Long id;
    private String fecha;
    private String statusCita;
    private Long pacienteId;
    private Long medicoId;

}
