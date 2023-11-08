package com.proyectootp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolDTO {
    private Integer idRol;
		
	@NotNull
    @NotEmpty
	private String nombreRol;
	
	@NotNull
    @NotEmpty
	private String descripcionRol;
}
