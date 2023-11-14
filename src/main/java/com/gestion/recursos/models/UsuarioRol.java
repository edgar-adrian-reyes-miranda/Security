package com.gestion.recursos.models;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="usuarioRol")
public class UsuarioRol {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long usuarioRolId;

	    @ManyToOne(fetch = FetchType.EAGER)
	    private Usuario usuario;

	    @ManyToOne
	    private Rol rol;

	    

}
