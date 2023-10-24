package com.midominio.evaluable2.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.evaluable2.app.entity.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long>, PagingAndSortingRepository<Libro, Long> {}
