package com.hospital.gestorcitas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date fechaConsulta;

    private String informe;

    @OneToOne(cascade = CascadeType.ALL)
    private Cita cita;

}
