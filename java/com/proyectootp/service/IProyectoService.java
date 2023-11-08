package com.proyectootp.service;

import java.util.List;
import org.springframework.data.repository.query.Param;

import com.proyectootp.model.Proyecto;

public interface IProyectoService {
	Proyecto guardar(Proyecto proyecto) throws Exception;
	Proyecto actualizar(Proyecto proyecto, Integer id) throws Exception;
	List<Proyecto> leerTodos() throws Exception;
	Proyecto leerPorId(Integer id) throws Exception;
	void borrar(Integer id) throws Exception;
	void borraDetalleProyecto(@Param("id") Integer id);

}

