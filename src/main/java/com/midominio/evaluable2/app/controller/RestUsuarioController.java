package com.midominio.evaluable2.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midominio.evaluable2.app.entity.Usuario;
import com.midominio.evaluable2.app.services.UsuarioService;

@RestController
@RequestMapping("/usuarios/rest")
public class RestUsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/listado")
	public Iterable<Usuario> listar() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Optional<Usuario> buscarPorId(@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	
	@DeleteMapping("/id/{id}")
	public void borrarPorId(@PathVariable Long id) {
		usuarioService.deleteById(id);
	}
	
	@PostMapping("")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
	
	@PutMapping("")
	public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
}
