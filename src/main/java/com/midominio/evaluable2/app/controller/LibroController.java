package com.midominio.evaluable2.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.midominio.evaluable2.app.entity.Libro;
import com.midominio.evaluable2.app.services.LibroService;

@Controller
@RequestMapping("/libros")
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	@ModelAttribute("titulo")
	public String titulo() {
		return "Libros";
	}
	
	@GetMapping("/listado")
	public String listar(Model model) {
		model.addAttribute("tituloH1", "Listado de libros");
		model.addAttribute("cantidad", libroService.count());
		model.addAttribute("libros", libroService.findAll());
		return "libros/listado";
	}
	
	@GetMapping("/borrar/{id}")
	public String borraPorId(@PathVariable("id") Long id) {
		if (id > 0) {
			libroService.deleteById(id);
		}
		return "redirect:/libros/listado";
	}
	
	@GetMapping({"/form", "/form/{id}"})
	public String mostrarLibro(@PathVariable(required=false) Long id, Model model) {
		if (id == null) {
			model.addAttribute("tituloH1", "Formulario de libro");
			model.addAttribute("libro", new Libro());
		} else {
			Optional<Libro> libro = null;
			
			if (id > 0) {
				libro = libroService.findById(id);
			} else {
				return "redirect:listar";
			}
			
			model.addAttribute("tituloH1", "Editar libro");
			model.addAttribute("libro", libro);
		}
		return "libros/form";
	}
	
	@PostMapping("/form")
	public String guardarLibro(@Validated Libro libro, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("tituloH1", "Formulario de libro");
			return "libros/form";
		}
		libroService.save(libro);
		return "redirect:/libros/listado";
	}
}
