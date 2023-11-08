package com.proyectootp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import com.proyectootp.exception.ModelNotFoundException;
import com.proyectootp.model.Proyecto;
import com.proyectootp.repo.IProyectoRepo;
import com.proyectootp.service.IProyectoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProyectoServiceImpl implements IProyectoService{


	private final IProyectoRepo repo;
	
	@Override
	public Proyecto guardar(Proyecto proyecto) throws Exception {
		return repo.save(proyecto);
	}

	@Override
	public Proyecto actualizar(Proyecto proyecto, Integer id) throws Exception {
		repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
		return repo.save(proyecto);
	}

	@Override
	public List<Proyecto> leerTodos() throws Exception {		
		return repo.findAll();
	}

	@Override
	public Proyecto leerPorId(Integer id) throws Exception {
		return repo.findById(id).orElse(new Proyecto());
	}

	@Override
	public void borrar(Integer id) throws Exception {
		repo.deleteById(id);
	}
	
	@Override
	public void borraDetalleProyecto(Integer id) {
		repo.borraDetalleProyecto(id);
	}

}