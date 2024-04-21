package com.hospital.gestorcitas.repository;

import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.StatusCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findByMedicoId(Long medicoId);

    List<Cita> findByStatusCita(StatusCita statusCita);

}
