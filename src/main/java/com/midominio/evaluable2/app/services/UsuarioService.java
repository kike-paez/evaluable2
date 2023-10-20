package com.midominio.evaluable2.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.evaluable2.app.dao.UsuarioRepository;
import com.midominio.evaluable2.app.entity.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public Long count() {
		return usuarioRepository.count();
	}
	
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	@Transactional
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	@Transactional
	public void save(Usuario usuario) {
		if (usuario.getId() != null && usuario.getId() > 0) {
			em.merge(usuario);		// actualiza
		} else {
			em.persist(usuario);	// inserta
		}
		usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
}
