package com.proyectootp.security;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRegistrationRequest {
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String genero;
	private String email;
	private String password;
}
