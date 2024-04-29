package com.hospital.gestorcitas.service.impl;

import com.hospital.gestorcitas.dto.CitaDTO;
import com.hospital.gestorcitas.dto.MedicoDTO;
import com.hospital.gestorcitas.mapper.CitaMapper;
import com.hospital.gestorcitas.mapper.MedicoMapper;
import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.Medico;
import com.hospital.gestorcitas.repository.MedicoRepository;
import com.hospital.gestorcitas.service.CitaService;
import com.hospital.gestorcitas.service.MedicoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository   medicoRepository;

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private MedicoMapper medicoMapper;



    @Override
    public List<MedicoDTO> getAllMedicos() {

        List<Medico> medicos = medicoRepository.findAll();
        return medicos.stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicoDTO> getMedicoById(Long id) {

        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        return medicoOptional.map(medicoMapper::toDTO);
    }

    @Override
    public MedicoDTO createMedico(MedicoDTO medicoDTO) {

        Medico medico = medicoMapper.toEntity(medicoDTO);
        medico = medicoRepository.save(medico);
        return medicoMapper.toDTO(medico);
    }

    @Override
    public MedicoDTO updateMedico(Long id, MedicoDTO medicoDTO) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);

        if (medicoOptional.isPresent()){
            Medico medico = medicoOptional.get();
            medico.setNombre(medicoDTO.getNombre());
            medico.setEmail(medicoDTO.getEmail());
            medico.setEspecialidad(medicoDTO.getEspecialidad());

            medico = medicoRepository.save(medico);
            return  medicoMapper.toDTO(medico);
        }
        return null;
    }

    @Override
    public void deleteMedico(Long id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isPresent()){
            Medico medico = medicoOptional.get();
            for (Cita cita: medico.getCitas()){
                citaService.deleteCita(cita.getId());
            }
            medicoRepository.deleteById(id);
        }
    }

    @Override
    public Collection<CitaDTO> getCitasByMedicoId(Long medicoId) {

        Optional<Medico> medicoOptional = medicoRepository.findById(medicoId);

        return medicoOptional
                .map(medico -> medico.getCitas().stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public List<MedicoDTO> getMedicosByEspecialidad(String especialidad) {
        List<Medico> medicoOptional = medicoRepository.findByEspecialidad(especialidad);

        return medicoOptional.stream().map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
