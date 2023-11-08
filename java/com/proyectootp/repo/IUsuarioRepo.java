package com.proyectootp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectootp.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{
	Usuario findOneByEmail(String email); 
}
