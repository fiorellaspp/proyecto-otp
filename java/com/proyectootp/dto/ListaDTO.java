package com.proyectootp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Todo Validaciones
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListaDTO {
	// private ProyectoDTO proyecto;
	private Integer idLista;
	private String descripcionLista;

	private String nombreLista;

	// private TableroDTO tablero; //
}
