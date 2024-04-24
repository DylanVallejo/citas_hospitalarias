package com.hospital.gestorcitas.mapper;


import com.hospital.gestorcitas.dto.PacienteDTO;
import com.hospital.gestorcitas.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    public PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNombre(paciente.getNombre());
        pacienteDTO.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDTO.setEnfermedad(paciente.isEnfermedad());
        return pacienteDTO;
    }

    public Paciente toEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setEnfermedad(pacienteDTO.isEnfermedad());
//        paciente.setCitas(pacienteDTO.get());
        return paciente;
    }
}