package com.proyectootp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//ToDo: relacion de uno a muchos
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(nullable = false, length = 30)
	private String nombre;
	
	@Column(nullable = false, length = 300)
	private String apellidos;
	
	@Column(nullable = false, length = 9)
	private String genero;
	
	@Column(nullable = false)
	private LocalDate fechaNacimiento;

	@Column(nullable = false, length = 80, unique = true)
	private String email;
	
	@Column(nullable = false, length = 60)
	private String password;
	
	@Column(nullable = false, length = 60)
	private String rol;
	
}