package com.hospital.gestorcitas.service.impl;

import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.PacienteDTO;
import com.hospital.gestorcitas.service.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class PacienteServiceImpl implements PacienteService {
    @Override
    public List<PacienteDTO> getAllPacientes() {
        return List.of();
    }

    @Override
    public Optional<PacienteDTO> getPacienteById() {
        return Optional.empty();
    }

    @Override
    public PacienteDTO createPaciente(PacienteDTO pacienteDTO) {
        return null;
    }

    @Override
    public PacienteDTO updatePaciente(Long id, PacienteDTO pacienteDTO) {
        return null;
    }

    @Override
    public void deletePaciente(Long id) {

    }

    @Override
    public Collection<CitaDTO> getCitasByPacienteId(Long pacienteId) {
        return List.of();
    }


}
