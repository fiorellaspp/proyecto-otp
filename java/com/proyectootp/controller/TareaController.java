package com.proyectootp.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectootp.dto.TareaDTO;
import com.proyectootp.model.Tarea;
// import com.proyectootp.service.IDetalleTareaService;
import com.proyectootp.service.ITareaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor
public class TareaController {
    private final ITareaService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public List<TareaDTO> leerTodos() throws Exception {
        List<TareaDTO> lista = service.leerTodos()
                .stream()
                .map(this::convertirADTO)
                .toList();
        return lista;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> readById(@PathVariable("id") Integer id) throws Exception {
        TareaDTO dto = convertirADTO(service.leerPorId(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TareaDTO> create(@Valid @RequestBody TareaDTO dto) throws Exception {
        Tarea obj = service.guardar(convertirAEntidad(dto));
        return new ResponseEntity<>(convertirADTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/detalle/{id}")
    public ResponseEntity<TareaDTO> modificarDetalle(@Valid @RequestBody TareaDTO
    dto, @PathVariable("id") Integer id) throws Exception {
    service.borraDetalleTarea(id);
    dto.setIdTarea(id);
    Tarea obj = service.actualizar(convertirAEntidad(dto), id);
    return new ResponseEntity<>(convertirADTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> update(@Valid @RequestBody TareaDTO dto, @PathVariable("id") Integer id)
            throws Exception {
        dto.setIdTarea(id);
        Tarea obj = service.actualizar(convertirAEntidad(dto), id);
        return new ResponseEntity<>(convertirADTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private TareaDTO convertirADTO(Tarea obj) {
        return mapper.map(obj, TareaDTO.class);
    }

    private Tarea convertirAEntidad(TareaDTO obj) {
        return mapper.map(obj, Tarea.class);
    }

    /* */
    @GetMapping("/usuario/{idUsuario}")
    public List<TareaDTO> listarTareasDeUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        List<TareaDTO> tareasDeUsuario = service.listarTareasPorIdUsuario(idUsuario)
                .stream()
                .map(this::convertirADTO)
                .toList();
        return tareasDeUsuario;
    }
}
