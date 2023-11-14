package com.gestion.recursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.recursos.models.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
