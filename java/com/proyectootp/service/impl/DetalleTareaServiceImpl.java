package com.proyectootp.service.impl;

import org.springframework.stereotype.Service;

import com.proyectootp.repo.IDetalleTareaRepo;
// import com.proyectootp.repo.ITareaRepo;
import com.proyectootp.service.IDetalleTareaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleTareaServiceImpl implements IDetalleTareaService {
	private final IDetalleTareaRepo repo;
	
	@Override
	public void borrar(Integer id) throws Exception {
		repo.deleteById(id);
	}
}
