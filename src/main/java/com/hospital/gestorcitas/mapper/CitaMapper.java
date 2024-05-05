package com.hospital.gestorcitas.mapper;


import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.Medico;
import com.hospital.gestorcitas.model.Paciente;
import com.hospital.gestorcitas.model.StatusCita;
import com.hospital.gestorcitas.repository.MedicoRepository;
import com.hospital.gestorcitas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class CitaMapper {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public CitaDTO toDto(Cita cita){
        CitaDTO citaDTO = new CitaDTO();

        citaDTO.setId(cita.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatedFecha = sdf.format(cita.getFecha());

        citaDTO.setFecha(formatedFecha);

        citaDTO.setCancelado(cita.isCancelado());
        citaDTO.setStatusCita(cita.getStatusCita().name());
        citaDTO.setPacienteId(cita.getPaciente().getId());
        citaDTO.setMedicoId(cita.getMedico().getId());
        return citaDTO;

    }

    public Cita toEntity(CitaDTO citaDTO) throws ParseException {
        Cita cita = new Cita();

        cita.setId(citaDTO.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = sdf.parse(citaDTO.getFecha());
        cita.setFecha(fecha);

        cita.setCancelado(citaDTO.getCancelado());
        cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));

        Optional<Paciente> paciente = pacienteRepository.findById(citaDTO.getPacienteId());
        Paciente pacienteBd = paciente.get();
        cita.setPaciente(pacienteBd);


        Optional<Medico> medico = medicoRepository.findById(citaDTO.getMedicoId());
        Medico medicoBd = medico.get();
        cita.setMedico(medicoBd);
        return cita;
    }


}
