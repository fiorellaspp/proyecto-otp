package com.proyectootp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectootp.exception.ModelNotFoundException;
import com.proyectootp.model.Usuario;
import com.proyectootp.repo.IUsuarioRepo;
import com.proyectootp.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{

	private final IUsuarioRepo repo;
	
	@Override
	public Usuario guardar(Usuario usuario) throws Exception {
		return repo.save(usuario);
	}

	@Override
	public Usuario actualizar(Usuario usuario, Integer id) throws Exception {
		repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
		return repo.save(usuario);
	}

	@Override
	public List<Usuario> leerTodos() throws Exception {		
		return repo.findAll();
	}

	@Override
	public Usuario leerPorId(Integer id) throws Exception {
		return repo.findById(id).orElse(new Usuario());
	}

	@Override
	public void borrar(Integer id) throws Exception {
		repo.deleteById(id);
	}

	@Override
	public Usuario leerPorEmail(String email) throws Exception {
		return repo.findOneByEmail(email);
	}
}