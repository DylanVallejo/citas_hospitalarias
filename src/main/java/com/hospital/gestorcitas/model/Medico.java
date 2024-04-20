package com.hospital.gestorcitas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;

    private String email;

    private String especialidad;

//medico sera el gestor de la relacion
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private Collection<Cita> citas;

}
