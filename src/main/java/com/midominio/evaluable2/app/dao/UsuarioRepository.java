package com.midominio.evaluable2.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.evaluable2.app.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>, PagingAndSortingRepository<Usuario, Long> {}
