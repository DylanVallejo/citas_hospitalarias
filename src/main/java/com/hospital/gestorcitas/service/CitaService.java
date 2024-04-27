package com.hospital.gestorcitas.service;

import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.StatusCita;
import org.aspectj.apache.bcel.classfile.Module;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface CitaService {

    List<CitaDTO> getAllCitas();

    Optional<CitaDTO> getCitaById(Long id);

    Cita createCita(CitaDTO citaDTO, Long idPaciente, Long idMedico) throws Exception;

    CitaDTO updateCita(Long id, CitaDTO citaDTO) throws ParseException;

    void deleteCita(Long id);

    List<CitaDTO> getCitasByPacienteId(Long pacienteId);

    List<CitaDTO> getCitasByMedicoId(Long medicoId);

    List<CitaDTO> getCitasByStatusCita(StatusCita statusCita);
}
