package com.proyectootp.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Todo Validaciones
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProyectoDTO {
	private Integer idProyecto;

	private String descripcionProyecto;

	@NotNull
	private LocalDate fechaCreacionProyecto;

	@NotNull
	private LocalDate fechaEntregaProyecto;

	@NotNull
	@NotEmpty
	private String nombreProyecto;

	@JsonManagedReference
	@NotNull
	private List<DetalleProyectoDTO> detallesProyecto;
}
