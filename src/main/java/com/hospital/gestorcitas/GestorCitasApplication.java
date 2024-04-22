package com.hospital.gestorcitas;

import com.hospital.gestorcitas.model.*;
import com.hospital.gestorcitas.repository.CitaRepository;
import com.hospital.gestorcitas.repository.ConsultaRepository;
import com.hospital.gestorcitas.repository.MedicoRepository;
import com.hospital.gestorcitas.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class GestorCitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorCitasApplication.class, args);
	}


//	@Bean
	CommandLineRunner start (PacienteRepository pacienteRepository, MedicoRepository medicoRepository, CitaRepository citaRepository, ConsultaRepository consultaRepository){
		return  args -> {
			Stream.of("Dylan" , "Joel", "Puerco")
					.forEach(nombre-> {
						Paciente paciente = new Paciente();
						paciente.setNombre(nombre);
						paciente.setFechaNacimiento(new Date());
						paciente.setEnfermedad(false);
						pacienteRepository.save(paciente);
					});
			Stream.of("Joel","Kael", "Rubick")
					.forEach(nombre -> {
						Medico medico = new Medico();
						medico.setNombre(nombre);;
						medico.setEmail(nombre);
						medico.setEmail(nombre + ( (int) Math.random() * 9)+ "@gmail.com");
						medico.setEspecialidad(Math.random() > 0.5 ? "Cardiología" : "Obstetricia");
						medicoRepository.save(medico);
					});

			Paciente pacienteUno = pacienteRepository.findById(1L).orElse(null);

			Medico medico = medicoRepository.findByNombre("Joel");

			Cita citaUno = new Cita();
			citaUno.setFecha(new Date());
			citaUno.setStatusCita(StatusCita.PENDIENTE);
			citaUno.setMedico(medico);
			citaUno.setPaciente(pacienteUno);
			citaRepository.save(citaUno);

			Cita citaBdddUno = citaRepository.findById(1L).orElse(null);

			Consulta consulta = new Consulta();
			consulta.setFechaConsulta(new Date());
			consulta.setCita(citaBdddUno);
			consulta.setInforme("Informe de la consulta");
			consultaRepository.save(consulta);
		};
	}

}
