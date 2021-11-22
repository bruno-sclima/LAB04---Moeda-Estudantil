package com.lab04.moedaEstudantil.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lab04.moedaEstudantil.model.Aluno;
import com.lab04.moedaEstudantil.model.Empresa;
import com.lab04.moedaEstudantil.model.Vantagem;
import com.lab04.moedaEstudantil.service.EmpresaService;
import com.lab04.moedaEstudantil.service.VantagemService;



@Controller
public class VantagemController {

	@Autowired
	VantagemService service;
	
	protected Long id = (long)0;
	protected Long idEmpresa= (long)0;
	
	@GetMapping("list-vantagens")
	public String getAllEmpresas(Model model) {
		List<Vantagem> list = service.getAllVantagens();

		model.addAttribute("vantagens", list);
		return "list-empresas";
	}
	@GetMapping("add-edit-vantagem")
	public String novoUsuario(@ModelAttribute("vantagem") Vantagem vantagem) {
		return "add-edit-vantagem";
	}
	
	@RequestMapping(path = {"/edit-vantagem", "/edit-vantagem/{id}"})
	public String editEmpresaById(Model model, @PathVariable("id") Optional<Long> id){
		if (id.isPresent()) {
			Vantagem entity = service.getVantagemById(id.get());
			model.addAttribute("vantagem", entity);
		} else {
			model.addAttribute("vantagem", new Empresa());
		}
		return "add-edit-empresa";
	}
	@RequestMapping(path = "/delete-vantagem/{id}")
	public String deleteEmpresaById(Model model, @PathVariable("id") Long id){
		service.deleteVantagemById(id);
		this.id = (long) 0;
		return "redirect:/";
	}

}