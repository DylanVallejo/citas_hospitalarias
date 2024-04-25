package com.hospital.gestorcitas.service.impl;

import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.MedicoDTO;
import com.hospital.gestorcitas.service.MedicoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MedicoServiceImpl implements MedicoService {
    @Override
    public List<MedicoDTO> getAllMedicos() {
        return List.of();
    }

    @Override
    public Optional<MedicoDTO> getMedicoById(Long id) {
        return Optional.empty();
    }

    @Override
    public MedicoDTO createMedico(MedicoDTO medicoDTO) {
        return null;
    }

    @Override
    public MedicoDTO updateMedico(Long id, MedicoDTO medicoDTO) {
        return null;
    }

    @Override
    public void deleteMedico(Long id) {

    }

    @Override
    public Collection<CitaDTO> getCitasByMedicoId(Long medicoId) {
        return List.of();
    }

    @Override
    public List<MedicoDTO> getMedicosByEspecialidad() {
        return List.of();
    }
}
