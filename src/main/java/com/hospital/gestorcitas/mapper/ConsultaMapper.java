package com.hospital.gestorcitas.mapper;


import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.ConsultaDTO;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.Consulta;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ConsultaMapper {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public ConsultaDTO toConsultaDto(Consulta consulta){
        ConsultaDTO consultaDto = new ConsultaDTO();

        consultaDto.setId(consulta.getId());
        consultaDto.setFechaConsulta(dateFormat.format(consulta.getFechaConsulta()));
        consultaDto.setInforme(consulta.getInforme());

        if (consulta.getCita() != null){
            Cita cita = consulta.getCita();
            CitaDTO citaDTO = new CitaDTO();

            citaDTO.setId(cita.getId());
            citaDTO.setFecha(dateFormat.format(cita.getFecha()));
            citaDTO.setCancelado(cita.isCancelado());
            citaDTO.setStatusCita(cita.getStatusCita().toString());
            citaDTO.setPacienteId(cita.getPaciente().getId());
            citaDTO.setMedicoId(cita.getMedico().getId());
            consultaDto.setCitaDTO(citaDTO);
        }
        return consultaDto;
    }


    public Consulta toEntity(ConsultaDTO consultaDTO) throws ParseException {
        Consulta consulta = new Consulta();
        consulta.setId(consultaDTO.getId());
        consulta.setFechaConsulta(dateFormat.parse(consultaDTO.getFechaConsulta()));
        consulta.setInforme(consultaDTO.getInforme());
        if(consultaDTO.getCitaDTO() != null){
            CitaDTO citaDTO = consultaDTO.getCitaDTO();
            Cita cita = new Cita();
            cita.setId(citaDTO.getId());
            consulta.setCita(cita);
        }
        return  consulta;
//        return null;
    }

}
