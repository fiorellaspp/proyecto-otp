package com.proyectootp.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Todo Validaciones
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
	private Integer idUsuario;
	
	@NotNull
	@NotEmpty
	private String nombreUsuario;
	
	@NotNull
	@NotEmpty
	private String apellidosUsuario;
	
	@NotNull
	@NotEmpty
	private String generoUsuario;
	
	@NotNull
	@NotEmpty
	private String rolUsuario;
	
	@NotNull
	@NotEmpty
	private String emailUsuario;
	
	@NotNull
	@NotEmpty
	private LocalDate fechaNacimientoUsuario;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull
	@NotEmpty
	@Size(min = 8, max = 60)
	private String passwordUsuario;
}