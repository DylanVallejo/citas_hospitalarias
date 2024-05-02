package com.hospital.gestorcitas.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.MedicoDTO;
import com.hospital.gestorcitas.dto.PacienteDTO;
import com.hospital.gestorcitas.exceptions.CitaNotFoundException;
import com.hospital.gestorcitas.mapper.CitaMapper;
import com.hospital.gestorcitas.mapper.MedicoMapper;
import com.hospital.gestorcitas.mapper.PacienteMapper;
import com.hospital.gestorcitas.model.*;
import com.hospital.gestorcitas.repository.CitaRepository;
import com.hospital.gestorcitas.repository.ConsultaRepository;
import com.hospital.gestorcitas.repository.MedicoRepository;
import com.hospital.gestorcitas.repository.PacienteRepository;
import com.hospital.gestorcitas.service.CitaService;
import com.hospital.gestorcitas.service.MedicoService;
import com.hospital.gestorcitas.service.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.invoke.CallSite;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private MedicoMapper medicoMapper;


    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoService medicoService;


    @Override
    public List<CitaDTO> getAllCitas() {
        List<Cita> citas  = citaRepository.findAll();
        return citas.stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CitaDTO> getCitaById(Long id) {

        Optional<Cita>  citaOptional= citaRepository.findById(id);
        return  citaOptional.map(citaMapper::toDto);
    }

    @Override
    public Cita createCita(CitaDTO citaDTO, Long idPaciente, Long idMedico) throws Exception {

        PacienteDTO pacienteDTO = pacienteService.getPacienteById(idPaciente).orElse(null);

        MedicoDTO medicoDTO = medicoService.getMedicoById(idMedico).orElse(null);

        if (pacienteDTO == null || medicoDTO == null){
            throw new Exception("Paciente o medico no encontrados");
//            return null
        }

        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        Medico medico = medicoMapper.toEntity(medicoDTO);
        Cita cita = citaMapper.toEntity(citaDTO); //espera un argumento pero envia 3
//        Cita cita = citaMapper.toEntity(citaDTO, paciente , medico);
        return citaRepository.save(cita);
    }

    @Override
    public CitaDTO updateCita(Long id, CitaDTO citaDTO) throws ParseException {
        Optional<Cita> citaOptional = citaRepository.findById(id);
        try {
            if (citaOptional.isPresent()){
                Cita cita = citaOptional.get();
                cita.setId(cita.getId());
                if (citaDTO.getFecha() != null ){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date fecha = sdf.parse(citaDTO.getFecha());
                }

                cita.setCancelado(citaDTO.getCancelado());
                cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));

                Optional<Paciente> pacienteOptional = pacienteRepository.findById(citaDTO.getPacienteId());
                Paciente paciente = pacienteOptional.get();
                cita.setPaciente(paciente);

                Optional<Medico> medicoOptional = medicoRepository.findById(citaDTO.getMedicoId());
                cita.setMedico(medicoOptional.get());

                return citaMapper.toDto(citaRepository.save(cita));
            }else {
                throw new CitaNotFoundException("La cita con el ID " + id + " no existe.");
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCita(Long id) {
        Optional<Cita> citaOptional = citaRepository.findById(id);

        if (citaOptional.isPresent()){
            Cita cita = citaOptional.get();

            if (cita.getConsulta() != null ){
                Consulta consulta = cita.getConsulta();
                consulta.setCita(null);
                consultaRepository.delete(consulta);
            }
            citaRepository.delete(cita);
        }

    }

    @Override
    public List<CitaDTO> getCitasByPacienteId(Long pacienteId) {
        List<Cita> citas =  citaRepository.findByPacienteId(pacienteId);
        return citas.stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> getCitasByMedicoId(Long medicoId) {

        List<Cita> citas = citaRepository.findByMedicoId(medicoId);

        return citas.stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> getCitasByStatusCita(StatusCita statusCita) {
        List<Cita> citas = citaRepository.findByStatusCita(statusCita);
        return citas.stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }
}
