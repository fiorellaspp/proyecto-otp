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
import com.proyectootp.dto.ProyectoDTO;
import com.proyectootp.model.Proyecto;
import com.proyectootp.service.IProyectoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/proyectos")
@RequiredArgsConstructor
public class ProyectoController {
    private final IProyectoService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping()
    public List<ProyectoDTO> leerTodos() throws Exception {
        List<ProyectoDTO> lista = service.leerTodos()
                .stream()
                .map(this::convertirADTO)
                .toList();
        return lista;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDTO> readById(@PathVariable("id") Integer id) throws Exception {
        ProyectoDTO dto = convertirADTO(service.leerPorId(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> create(@Valid @RequestBody ProyectoDTO dto) throws Exception {
        Proyecto obj = service.guardar(convertirAEntidad(dto));
        return new ResponseEntity<>(convertirADTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/detalle/{id}")
    public ResponseEntity<ProyectoDTO> modificarDetalle(@Valid @RequestBody ProyectoDTO dto,
            @PathVariable("id") Integer id) throws Exception {
        service.borraDetalleProyecto(id);
        dto.setIdProyecto(id);
        Proyecto obj = service.actualizar(convertirAEntidad(dto), id);
        return new ResponseEntity<>(convertirADTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoDTO> update(@Valid @RequestBody ProyectoDTO dto, @PathVariable("id") Integer id)
            throws Exception {
        dto.setIdProyecto(id);
        Proyecto obj = service.actualizar(convertirAEntidad(dto), id);
        return new ResponseEntity<>(convertirADTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ProyectoDTO convertirADTO(Proyecto obj) {
        return mapper.map(obj, ProyectoDTO.class);
    }

    private Proyecto convertirAEntidad(ProyectoDTO obj) {
        return mapper.map(obj, Proyecto.class);
    }
}
