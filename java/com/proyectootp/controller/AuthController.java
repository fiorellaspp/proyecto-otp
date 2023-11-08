package com.proyectootp.controller;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyectootp.dto.UsuarioDTO;
import com.proyectootp.model.Usuario;
import com.proyectootp.security.JwtRegistrationRequest;
import com.proyectootp.security.JwtRequest;
import com.proyectootp.security.JwtResponse;
import com.proyectootp.security.JwtTokenUtil;
import com.proyectootp.security.JwtUserDetailsService;
import com.proyectootp.service.IUsuarioService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

	private final IUsuarioService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
  
    @Qualifier("defaultMapper")
	private final ModelMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception{
        authenticate(req.getUsername(), req.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody JwtRegistrationRequest req) throws Exception{
        
    	registration(req);
    	
        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);    	
    	
        // return new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    private void authenticate(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
    public void registration(JwtRegistrationRequest request) throws Exception {
    	UsuarioDTO  dto = new UsuarioDTO();
    	dto.setNombreUsuario(request.getNombre());
    	dto.setApellidosUsuario(request.getApellido());
    	dto.setGeneroUsuario(request.getGenero());
    	dto.setRolUsuario("USER");
    	dto.setEmailUsuario(request.getEmail());
    	dto.setFechaNacimientoUsuario(request.getFechaNacimiento());    	
    	dto.setPasswordUsuario(passwordEncoder.encode(request.getPassword()));    	
    	
    	service.guardar(convertirAEntidad(dto));
    }
    
    private UsuarioDTO convertirADTO(Usuario obj) {
		return mapper.map(obj, UsuarioDTO.class);
	}
	
	private Usuario convertirAEntidad(UsuarioDTO obj) {
		return mapper.map(obj, Usuario.class);
	}
}
