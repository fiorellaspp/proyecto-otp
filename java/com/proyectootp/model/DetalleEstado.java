package com.proyectootp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class DetalleEstado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idDetalleEstado;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false, foreignKey = @ForeignKey(name = "FK_DetalleEstado_Estado"))
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "id_proyecto", nullable = false, foreignKey = @ForeignKey(name = "FK_DetalleEstado_Proyecto"))
	private Proyecto proyecto;
	
	@Column(nullable = false)
	private LocalDate fechaModificacion;
}
