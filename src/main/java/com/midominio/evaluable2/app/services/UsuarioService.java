package com.midominio.evaluable2.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.midominio.evaluable2.app.dao.UsuarioRepository;
import com.midominio.evaluable2.app.entity.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Long count() {
		return usuarioRepository.count();
	}
	
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public Page<Usuario> listar(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}
}
