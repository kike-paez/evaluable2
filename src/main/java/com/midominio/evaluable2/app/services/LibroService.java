package com.midominio.evaluable2.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.evaluable2.app.dao.LibroRepository;
import com.midominio.evaluable2.app.entity.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public Long count() {
		return libroRepository.count();
	}
	
	public Iterable<Libro> findAll() {
		return libroRepository.findAll();
	}
	
	@Transactional
	public void deleteById(Long id) {
		libroRepository.deleteById(id);
	}
	
	@Transactional
	public void save(Libro libro) {
		if (libro.getId() != null && libro.getId() > 0) {
			em.merge(libro);	// actualiza
		} else {
			em.persist(libro);	// inserta
		}
		libroRepository.save(libro);
	}
	
	public Optional<Libro> findById(Long id) {
		return libroRepository.findById(id);
	}
}
