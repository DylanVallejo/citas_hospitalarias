package com.hospital.gestorcitas.repository;

import com.hospital.gestorcitas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Medico findByNombre(String nombre);

    List<Medico> findByEspecialidad(String especialidad);

}
