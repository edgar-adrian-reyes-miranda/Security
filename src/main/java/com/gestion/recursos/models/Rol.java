package com.gestion.recursos.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;


@Entity
@Data
@Table(name="Rol")
public class Rol {
	
	 @Id
	    private Long rolId;
	    private String rolNombre;

	    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
	    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

	 	

}
