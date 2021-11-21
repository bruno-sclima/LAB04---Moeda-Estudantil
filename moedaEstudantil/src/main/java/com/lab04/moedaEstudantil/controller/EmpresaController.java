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
import com.lab04.moedaEstudantil.service.EmpresaService;

@Controller
public class EmpresaController {

	@Autowired
	EmpresaService service;
	
	protected Long id = (long)0;
	
	@GetMapping("list-empresas")
	public String getAllEmpresas(Model model) {
		List<Empresa> list = service.getAllEmpresas();

		model.addAttribute("empresas", list);
		return "list-empresas";
	}
	@GetMapping("add-edit-empresa")
	public String novoUsuario(@ModelAttribute("empresa") Empresa empresa) {
		return "add-edit-empresa";
	}
	@GetMapping("login-empresa")
	public String pagLogin(@ModelAttribute("empresa") Aluno aluno) {
		
		return "login-empresa";
	}
	@GetMapping("logOutEmpresa")
	public String logOutE() {
		this.id=(long) 0;
		return "redirect:/";
	}
	@GetMapping("perfilEmpresa")
	public String perfil(Model model,@ModelAttribute("empresa") Empresa aluno) {
		Empresa a = service.getAEmpresaById(id);
		model.addAttribute("empresas", a);
		return "perfil-empresa";
	}
	@RequestMapping(path = "/createEmpresa", method = RequestMethod.POST)
	public String createOrUpdateEmployee(Empresa empresa) {	
		String url = "redirect:/";
		if(id!=0) url = "redirect:/homeLogEmp";
		service.createOrUpdateEmpresa(empresa);
		return url;
	}
	@RequestMapping(path = {"/edit-empresa", "/edit-empresa/{id}"})
	public String editEmpresaById(Model model, @PathVariable("id") Optional<Long> id){
		if (id.isPresent()) {
			Empresa entity = service.getAEmpresaById(id.get());
			model.addAttribute("empresa", entity);
		} else {
			model.addAttribute("empresa", new Empresa());
		}
		return "add-edit-empresa";
	}
	@RequestMapping(path = "/delete-empresa/{id}")
	public String deleteEmpresaById(Model model, @PathVariable("id") Long id){
		service.deleteEmpresaById(id);
		this.id = (long) 0;
		return "redirect:/";
	}
	
	@RequestMapping(path = "/logEmpresa", method = RequestMethod.POST)
	public String login(Empresa empresa) {
		var res = "erro";
		List<Empresa> list = service.getAllEmpresas();
		
		List<Empresa> a = list.stream().filter((f) -> f.getEmail().equals(empresa.getEmail()) && f.getSenha().equals(empresa.getSenha())).collect(Collectors.toList());
		if(!a.isEmpty()) {
			res = "homeLogEmp";
			id = a.get(0).getId();
		}
		return res;
	}
}
