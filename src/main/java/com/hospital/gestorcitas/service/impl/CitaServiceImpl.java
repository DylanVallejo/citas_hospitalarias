package com.hospital.gestorcitas.service.impl;

import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.StatusCita;
import com.hospital.gestorcitas.service.CitaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CitaServiceImpl implements CitaService {
    @Override
    public List<CitaDTO> getAllCitas() {
        return List.of();
    }

    @Override
    public Optional<CitaDTO> getCitaById(Long id) {
        return Optional.empty();
    }

    @Override
    public Cita createCita(CitaDTO citaDTO, Long idPaciente, Long idMedico) {
        return null;
    }

    @Override
    public CitaDTO updateCita(Long id, CitaDTO citaDTO) {
        return null;
    }

    @Override
    public void deleteCita(Long id) {

    }

    @Override
    public List<CitaDTO> getCitasByPacienteId(Long pacienteId) {
        return List.of();
    }

    @Override
    public List<CitaDTO> getCitasByMedicoId(Long medicoId) {
        return List.of();
    }

    @Override
    public List<CitaDTO> getCitasByStatusCita(StatusCita statusCita) {
        return List.of();
    }
}
