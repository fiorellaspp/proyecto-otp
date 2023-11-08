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
public class DetalleProyectoDTO {
    @JsonBackReference
	private ProyectoDTO idProyectoDetP;
	
	private UsuarioDTO idUsuarioDetP;
	
	private RolDTO idRolDetP;
}
