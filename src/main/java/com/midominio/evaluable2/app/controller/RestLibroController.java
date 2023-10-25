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

import com.midominio.evaluable2.app.entity.Libro;
import com.midominio.evaluable2.app.services.LibroService;

@RestController
@RequestMapping("/libros/rest")
public class RestLibroController {
	
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/listado")
	public Iterable<Libro> listar() {
		return libroService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Optional<Libro> buscarPorId(@PathVariable Long id) {
		return libroService.findById(id);
	}
	
	@DeleteMapping("/id/{id}")
	public void borrarPorId(@PathVariable Long id) {
		libroService.deleteById(id);
	}
	
	@PostMapping("")
	public Libro crearLibro(@RequestBody Libro libro) {
		return libroService.save(libro);
	}
	
	@PutMapping("")
	public Libro actualizarLibro(@RequestBody Libro libro) {
		return libroService.save(libro);
	}
}
