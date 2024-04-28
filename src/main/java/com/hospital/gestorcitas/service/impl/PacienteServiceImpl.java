package com.hospital.gestorcitas.service.impl;

import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.PacienteDTO;
import com.hospital.gestorcitas.mapper.CitaMapper;
import com.hospital.gestorcitas.mapper.PacienteMapper;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.Paciente;
import com.hospital.gestorcitas.repository.PacienteRepository;
import com.hospital.gestorcitas.service.CitaService;
import com.hospital.gestorcitas.service.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;


    @Autowired
    private CitaService citaService;


    @Autowired
    private PacienteMapper pacienteMapper;


    @Autowired
    private CitaMapper citaMapper;


    @Override
    public List<PacienteDTO> getAllPacientes() {

        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(pacienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PacienteDTO> getPacienteById(Long id) {
        try {
            Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
            return pacienteOptional.map(pacienteMapper::toDTO);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public PacienteDTO createPaciente(PacienteDTO pacienteDTO) {

        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toDTO(paciente);
    }

    @Override
    public PacienteDTO updatePaciente(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            paciente.setNombre(pacienteDTO.getNombre());
            paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
            paciente.setEnfermedad(pacienteDTO.isEnfermedad());
            paciente = pacienteRepository.save(paciente);
            return pacienteMapper.toDTO(paciente);
        }
        return null;
    }

    @Override
    public void deletePaciente(Long id) {

        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();

            for (Cita cita : paciente.getCitas()){
                citaService.deleteCita(cita.getId());
            }
            pacienteRepository.deleteById(id);
        }
    }

    @Override
    public Collection<CitaDTO> getCitasByPacienteId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        return pacienteOptional.map(paciente -> paciente.getCitas().stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList()))
                .orElse(null);
    }


}
