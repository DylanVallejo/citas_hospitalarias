package com.hospital.gestorcitas.service.impl;

import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.ConsultaDTO;
import com.hospital.gestorcitas.mapper.CitaMapper;
import com.hospital.gestorcitas.mapper.ConsultaMapper;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.Consulta;
import com.hospital.gestorcitas.model.StatusCita;
import com.hospital.gestorcitas.repository.CitaRepository;
import com.hospital.gestorcitas.repository.ConsultaRepository;
import com.hospital.gestorcitas.service.CitaService;
import com.hospital.gestorcitas.service.ConsultaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaRepository citaRepository;


    @Override
    public List<ConsultaDTO> getAllConsultas() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(consultaMapper::toConsultaDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ConsultaDTO> getConsultaById(Long id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);
        return consulta.map(consultaMapper::toConsultaDto);
    }

    @Override
    public ConsultaDTO createConsulta(Long citaId, ConsultaDTO consultaDTO) throws ParseException {
        CitaDTO citaDTO = citaService.getCitaById(citaId).orElseThrow(() -> new EntityNotFoundException("Cita not found"));

        Consulta consulta = new Consulta();
        consulta.setCita(citaMapper.toEntity(citaDTO));
        consulta.setFechaConsulta(new Date());
        consulta.setInforme(consultaDTO.getInforme());

        Consulta createConsulta = consultaRepository.save(consulta);
        return consultaMapper.toConsultaDto(createConsulta);
    }

    @Override
    public ConsultaDTO updateConsulta(Long consultaId, ConsultaDTO consultaDTO) throws ParseException {
        Optional<Consulta> consultaOptional = consultaRepository.findById(consultaId);
        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();

            consulta.setFechaConsulta(consultaDTO.getFechaConsultaAsDate());
            consulta.setInforme(consultaDTO.getInforme());
            Consulta updateConsulta = consultaRepository.save(consulta);
//            Cita cita = consulta.getCita();
            CitaDTO citaDTO = consultaDTO.getCitaDTO();

            if (citaDTO != null) {
                Cita cita = consulta.getCita();
                if (cita != null) {
                    cita.setFecha(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(citaDTO.getFecha()));
                    cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));
                    citaRepository.save(cita);
                }
                citaService.updateCita(cita.getId(), citaDTO);
            }
            return consultaMapper.toConsultaDto(updateConsulta);
        }
        return null;
    }

    @Override
    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    @Override
    public List<ConsultaDTO> getConsultasByInformeContaining(String searchTerm) {
        List<Consulta> consultas = consultaRepository.findByInformeContainingIgnoreCase(searchTerm);
        return consultas.stream()
                .map(consultaMapper::toConsultaDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> getConsultasByCitas(Cita cita) {
        List<Consulta> consultas = consultaRepository.findByCita(cita);
        return consultas.stream()
                .map(consultaMapper::toConsultaDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> getConsultasByCitaId(Long id) throws ParseException {
        CitaDTO citaDTO = citaService.getCitaById(id).orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
        Cita cita = citaMapper.toEntity(citaDTO);
        List<ConsultaDTO> consultas = getConsultasByCitas(cita);
        return consultas;
    }
}
