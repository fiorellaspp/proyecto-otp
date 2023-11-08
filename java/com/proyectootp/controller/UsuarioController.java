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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectootp.dto.UsuarioDTO;
import com.proyectootp.model.Usuario;
import com.proyectootp.security.JwtTokenUtil;

import com.proyectootp.service.IUsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final IUsuarioService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public List<UsuarioDTO> leerTodos() throws Exception {
        List<UsuarioDTO> lista = service.leerTodos()
                .stream()
                .map(this::convertirADTO)
                .toList();
        return lista;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> readById(@PathVariable("id") Integer id) throws Exception {
        UsuarioDTO dto = convertirADTO(service.leerPorId(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto) throws Exception {
        Usuario obj = service.guardar(convertirAEntidad(dto));
        return new ResponseEntity<>(convertirADTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@Valid @RequestBody UsuarioDTO dto, @PathVariable("id") Integer id)
            throws Exception {
        dto.setIdUsuario(id);
        Usuario obj = service.actualizar(convertirAEntidad(dto), id);
        return new ResponseEntity<>(convertirADTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UsuarioDTO convertirADTO(Usuario obj) {
        return mapper.map(obj, UsuarioDTO.class);
    }

    private Usuario convertirAEntidad(UsuarioDTO obj) {
        return mapper.map(obj, Usuario.class);
    }

    /* */
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/user")
    public ResponseEntity<String> obtenerNombreDeUsuario(@RequestParam("token") String token) {
        String nombreDeUsuario = jwtTokenUtil.getUsernameFromToken(token);

        if (nombreDeUsuario != null) {
            return ResponseEntity.ok(nombreDeUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorEmail(@RequestParam("email") String email) throws Exception {
        UsuarioDTO usuarioDTO = convertirADTO(service.leerPorEmail(email));
        if (usuarioDTO != null) {
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
