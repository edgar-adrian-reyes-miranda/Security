package com.gestion.recursos.services;

import java.util.Set;

import com.gestion.recursos.models.Usuario;
import com.gestion.recursos.models.UsuarioRol;

public interface UsuarioService {
	 public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

	    public Usuario obtenerUsuario(String username);

	    public void eliminarUsuario(Long usuarioId);

}
