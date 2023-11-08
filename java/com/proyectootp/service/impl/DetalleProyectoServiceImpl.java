package com.proyectootp.service.impl;

import org.springframework.stereotype.Service;

import com.proyectootp.repo.IDetalleProyectoRepo;
import com.proyectootp.service.IDetalleProyectoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleProyectoServiceImpl implements IDetalleProyectoService {
    private final IDetalleProyectoRepo repo;
	
	@Override
	public void borrar(Integer id) throws Exception {
		repo.deleteById(id);
	}
}
