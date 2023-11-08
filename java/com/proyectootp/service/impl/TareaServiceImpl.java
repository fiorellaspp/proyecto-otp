package com.proyectootp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectootp.exception.ModelNotFoundException;
import com.proyectootp.model.Tarea;
// import com.proyectootp.model.Usuario;
import com.proyectootp.repo.ITareaRepo;
// import com.proyectootp.repo.IUsuarioRepo;
import com.proyectootp.service.ITareaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements ITareaService {

	private final ITareaRepo repo;

	@Override
	public Tarea guardar(Tarea tarea) throws Exception {
		return repo.save(tarea);
	}

	@Override
	public Tarea actualizar(Tarea tarea, Integer id) throws Exception {
		repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
		return repo.save(tarea);
	}

	@Override
	public List<Tarea> leerTodos() throws Exception {
		return repo.findAll();
	}

	@Override
	public Tarea leerPorId(Integer id) throws Exception {
		return repo.findById(id).orElse(new Tarea());
	}

	@Override
	public void borrar(Integer id) throws Exception {
		repo.deleteById(id);
	}

	@Override
	public void borraDetalleTarea(Integer id) {
		repo.borraDetalleTarea(id);
	}
/* */
	@Override
	public List<Tarea> listarTareasPorIdUsuario(Integer idUsuario) throws Exception {
		return repo.findTareasByUsuarioId(idUsuario);
	}
}
