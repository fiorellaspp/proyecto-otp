package com.proyectootp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.AssertFalse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class DetalleProyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idDetalleProyecto;
	
	@ManyToOne
	@JoinColumn(name = "id_proyecto", nullable = false, foreignKey = @ForeignKey(name ="FK_DETALLEPROYECTO_PROYECTO"))
	private Proyecto proyecto;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLEPROYECTO_USUARIO"))
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLEPROYECTO_ROL"))
	private Rol rol;
}
