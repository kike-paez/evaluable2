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

import com.midominio.evaluable2.app.entity.Usuario;
import com.midominio.evaluable2.app.services.UsuarioService;

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
	public String listarTodosLosUsuario(Model model) {
		model.addAttribute("tituloH1", "Listado de usuarios");
		model.addAttribute("cantidad", usuarioService.count());
		model.addAttribute("usuarios", usuarioService.findAll());
		return "usuarios/listado";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarPorId(@PathVariable("id") Long id) {
		if (id > 0) {
			usuarioService.deleteById(id);
		}
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
	public String guardar(@Validated Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("tituloH1", "Formulario de usuario");
			return "usuarios/form";
		}
		usuarioService.save(usuario);
		return "redirect:/usuarios/listado";
	}
}
