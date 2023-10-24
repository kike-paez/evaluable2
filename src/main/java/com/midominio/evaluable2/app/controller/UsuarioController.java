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

import com.midominio.evaluable2.app.entity.Usuario;
import com.midominio.evaluable2.app.services.UsuarioService;
import com.midominio.evaluable2.app.utils.paginator.PageRender;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute("titulo")
	public String titulo() {
		return "Usuarios";
	}
	
	@GetMapping("/listado")
	public String listarTodosLosUsuario(@RequestParam(defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Usuario> usuarios = usuarioService.listar(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/usuarios/listado", usuarios); 
		
		model.addAttribute("tituloH1", "Listado de usuarios");
		model.addAttribute("cantidad", usuarioService.count());
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("page", pageRender);
		return "usuarios/listado";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarPorId(@PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			usuarioService.deleteById(id);
		}
		flash.addFlashAttribute("warning", "Artículo borrado con éxito");
		return "redirect:/usuarios/listado";
	}
	
	@GetMapping({"/form", "/form/{id}"})
	public String mostrarUsuario(@PathVariable(required=false) Long id, Model model) {
		if (id == null) {
			model.addAttribute("tituloH1", "Formulario de usuario");
			model.addAttribute("usuario", new Usuario());
		} else {
			Optional<Usuario> usuario = null;
			
			if (id > 0) {
				usuario = usuarioService.findById(id);
			} else {
				return "redirect:/usuarios/listado";
			}
			
			model.addAttribute("tituloH1", "Editar usuario");
			model.addAttribute("usuario", usuario);
		}
		return "usuarios/form";
	}
	
	@PostMapping("/form")
	public String guardar(@Validated Usuario usuario, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("tituloH1", "Formulario de usuario");
			return "usuarios/form";
		}
		usuarioService.save(usuario);
		flash.addFlashAttribute("success", "Artículo guardado con éxito");
		return "redirect:/usuarios/listado";
	}
}
