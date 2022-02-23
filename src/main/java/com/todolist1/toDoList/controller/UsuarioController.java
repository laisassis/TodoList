package com.todolist1.toDoList.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist1.toDoList.model.UserLogin;
import com.todolist1.toDoList.model.Usuario;
import com.todolist1.toDoList.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	

	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> autenticationUsuario(@RequestBody Optional<UserLogin> usuario) {
		return usuarioService.Logar(usuario)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity <Usuario> Post(@RequestBody Usuario usuario) {

		Usuario usuarioResp = usuarioService.CadastrarUsuario(usuario);
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResp);
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().build();
		}
	}
}
