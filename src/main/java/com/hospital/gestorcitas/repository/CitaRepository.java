package com.hospital.gestorcitas.repository;

import com.hospital.gestorcitas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}
