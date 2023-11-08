package com.proyectootp.service;

import java.util.List;
// import java.util.Locale.Category;

import com.proyectootp.model.Usuario;

public interface IUsuarioService {
	Usuario guardar(Usuario usuario) throws Exception;
	Usuario actualizar(Usuario usuario, Integer id) throws Exception;
	List<Usuario> leerTodos() throws Exception;
	Usuario leerPorId(Integer id) throws Exception;
	void borrar(Integer id) throws Exception;
	/* */
	Usuario leerPorEmail(String email) throws Exception;
}
