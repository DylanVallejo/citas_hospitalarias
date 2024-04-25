package com.hospital.gestorcitas.service.impl;

import com.hospital.gestorcitas.dto.ConsultaDTO;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.service.ConsultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ConsultaServiceImpl implements ConsultaService {
    @Override
    public List<ConsultaDTO> getAllConsultas() {
        return List.of();
    }

    @Override
    public Optional<ConsultaDTO> getConsultaById(Long id) {
        return Optional.empty();
    }

    @Override
    public ConsultaDTO createConsulta(Long citaId, ConsultaDTO consultaDTO) throws ParseException {
        return null;
    }

    @Override
    public ConsultaDTO updateConsulta(Long consultaId) {
        return null;
    }

    @Override
    public void deleteConsulta(Long id) {

    }

    @Override
    public List<ConsultaDTO> getConsultasByInformeContaining(String searchTerm) {
        return List.of();
    }

    @Override
    public List<ConsultaDTO> getConsultasByCitas(Cita cita) {
        return List.of();
    }

    @Override
    public List<ConsultaDTO> getConsultasByCitaId(Long id) throws ParseException {
        return List.of();
    }
}
