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
public class TareaDTO {
	private Integer idTarea;
	
	
	private ListaDTO listaTarea;
	
	@NotNull
	@NotEmpty
	private String nombreTarea;
	
	@NotNull
	@NotEmpty
	private String descripcionTarea;
	
	@NotNull	
	private LocalDate fechaEntregaTarea;
	
	@NotNull
	private Boolean finalizadoTarea;
	
	@JsonManagedReference
    @NotNull
    private List<DetalleTareaDTO> detalles;

}
