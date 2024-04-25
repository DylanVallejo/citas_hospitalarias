package com.hospital.gestorcitas.service;

import com.hospital.gestorcitas.dto.ConsultaDTO;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.Consulta;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ConsultaService {

    List<ConsultaDTO> getAllConsultas();

    Optional<ConsultaDTO> getConsultaById(Long id);

    ConsultaDTO createConsulta(Long citaId, ConsultaDTO consultaDTO) throws ParseException;

    ConsultaDTO updateConsulta(Long consultaId);

    void deleteConsulta(Long id);

    List<ConsultaDTO> getConsultasByInformeContaining(String searchTerm);

    List<ConsultaDTO> getConsultasByCitas(Cita cita);

    List<ConsultaDTO> getConsultasByCitaId(Long id) throws  ParseException;

}
