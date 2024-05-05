package com.hospital.gestorcitas.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private boolean cancelado;

    @Enumerated(EnumType.STRING)
    private StatusCita statusCita;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @OneToOne(mappedBy = "cita")
    private Consulta consulta;
}
