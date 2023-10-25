package com.midominio.evaluable2.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.midominio.evaluable2.app.entity.Libro;
import com.midominio.evaluable2.app.services.LibroService;
import com.midominio.evaluable2.app.utils.paginator.PageRender;

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
	public String listar(@RequestParam(required=false) String autor, @RequestParam(defaultValue="0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Libro> libros = libroService.listar(pageRequest);
		PageRender<Libro> pageRender = new PageRender<>("/libros/listado", libros);
		
		if (autor == null) {
			model.addAttribute("libros", libros);
			model.addAttribute("mostrarPaginador", true);
		} else {
			model.addAttribute("libros", libroService.findByAutor(autor));
			model.addAttribute("mostrarVolver", true);
		}
		
		model.addAttribute("tituloH1", "Listado de libros");
		model.addAttribute("cantidad", libroService.count());
		model.addAttribute("page", pageRender);
		return "libros/listado";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarPorId(@PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			libroService.deleteById(id);
		}
		flash.addFlashAttribute("warning", "Artículo borrado con éxito");
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
	public String guardarLibro(@Validated Libro libro, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("tituloH1", "Formulario de libro");
			return "libros/form";
		}
		libroService.save(libro);
		flash.addFlashAttribute("success", "Artículo guardado con éxito");
		return "redirect:/libros/listado";
	}
}
