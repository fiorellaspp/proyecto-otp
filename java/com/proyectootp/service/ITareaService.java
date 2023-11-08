package com.proyectootp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.proyectootp.model.Tarea;


public interface ITareaService {
	Tarea guardar(Tarea Tarea) throws Exception;
	Tarea actualizar(Tarea tarea, Integer id) throws Exception;
	List<Tarea> leerTodos() throws Exception;
	Tarea leerPorId(Integer id) throws Exception;
	void borrar(Integer id) throws Exception;
	void borraDetalleTarea(@Param("id") Integer id);

	/* */
	List<Tarea> listarTareasPorIdUsuario(Integer idUsuario) throws Exception;
}
