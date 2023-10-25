package com.midominio.evaluable2.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.midominio.evaluable2.app.dao.LibroRepository;
import com.midominio.evaluable2.app.entity.Libro;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	public Long count() {
		return libroRepository.count();
	}
	
	public Iterable<Libro> findAll() {
		return libroRepository.findAll();
	}
	
	public void deleteById(Long id) {
		libroRepository.deleteById(id);
	}
	
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}
	
	public Optional<Libro> findById(Long id) {
		return libroRepository.findById(id);
	}

	public List<Libro> findByAutor(String autor) {
		List<Libro> listaFiltrada = new ArrayList<>();
		
		for (Libro libro: findAll()) {
			if (autor.equals(libro.getAutor())) {
				listaFiltrada.add(libro);
			}
		}
		return listaFiltrada;
	}
	
	public Page<Libro> listar(Pageable pageable) {
		return libroRepository.findAll(pageable);
	}
}
