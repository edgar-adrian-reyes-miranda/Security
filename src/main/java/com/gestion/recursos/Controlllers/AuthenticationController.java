package com.gestion.recursos.Controlllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.recursos.configuration.JwtUtils;
import com.gestion.recursos.models.JwtRequst;
import com.gestion.recursos.models.JwtResponse;
import com.gestion.recursos.models.Usuario;
import com.gestion.recursos.services.Impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserDetailsServiceImpl userDetailsService;

	    @Autowired
	    private JwtUtils jwtUtils;

	@PostMapping("/generate-token")
	public ResponseEntity<?> generarToken(@RequestBody JwtRequst jwtRequest) throws Exception {
		try {
			autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (BadCredentialsException e) {
			throw new Exception("Credenciales inválidas");
		} catch (DisabledException e) {
			throw new Exception("Usuario deshabilitado");
		}

		UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void autenticar(String username,String password) throws Exception {
	        try{
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
	        }catch (DisabledException exception){
	            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
	        }catch (BadCredentialsException e){
	            throw  new Exception("Credenciales invalidas " + e.getMessage());
	        }
	    }

	@GetMapping("/actual-usuario")
	public ResponseEntity<?> obtenerUsuarioActual(Principal principal) {
		if (principal != null) {
			try {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(principal.getName());
				return ResponseEntity.ok(userDetails);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener información del usuario");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay usuario autenticado");
		}
	}



}
