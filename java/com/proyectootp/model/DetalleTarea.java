package com.proyectootp.model;

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
public class DetalleTarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idDetalleTarea;
	
	@ManyToOne
	@JoinColumn(name = "id_tarea", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLETAREA_TAREA"))
	private Tarea tarea;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLETAREA_USUARIO"))
	private Usuario usuario;
	
	@Column(length = 500)
	private String comentario;
}
