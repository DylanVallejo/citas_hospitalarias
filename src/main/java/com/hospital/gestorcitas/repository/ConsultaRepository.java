package com.hospital.gestorcitas.repository;

import com.hospital.gestorcitas.model.Cita;
import com.hospital.gestorcitas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository  extends JpaRepository<Consulta, Long> {

    List<Consulta> findByCita(Cita cita);


    List<Consulta> findByInformeContainingIgnoreCase(String searchTerm);

}
