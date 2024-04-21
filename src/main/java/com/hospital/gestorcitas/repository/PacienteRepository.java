package com.hospital.gestorcitas.repository;

import com.hospital.gestorcitas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    Paciente findByNombre(String nombre);


}
