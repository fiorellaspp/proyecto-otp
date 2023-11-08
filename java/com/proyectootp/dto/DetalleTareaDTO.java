package com.proyectootp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleTareaDTO {
	@JsonBackReference
	private TareaDTO tarea;
	
	private UsuarioDTO usuario;
	
	private String comentario;
}
