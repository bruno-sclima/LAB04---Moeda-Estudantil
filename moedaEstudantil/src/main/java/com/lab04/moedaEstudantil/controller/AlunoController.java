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
import com.lab04.moedaEstudantil.service.AlunoService;
@Controller

public class AlunoController {

	@Autowired
	AlunoService service;
	protected Long id = (long) 0;
	@GetMapping("list-alunos")
	public String getAllAlunos(Model model){
		List<Aluno> list = service.getAllAlunos();

		model.addAttribute("alunos", list);
		return "list-alunos";
	}
	@GetMapping("add-edit-aluno")
	public String novoUsuario(@ModelAttribute("aluno") Aluno aluno) {
		return "add-edit-aluno";
	}
	@GetMapping("home")
	public String home() {
		String url = "index";
		if(id!=0) url = "homeLog";
		return url;
	}
	@GetMapping("homeLog")
	public String homeLog() {
		return "homeLog";
	}
	@GetMapping("login-aluno")
	public String pagLogin(@ModelAttribute("aluno") Aluno aluno) {
		
		return "login-aluno";
	}
	@GetMapping("perfilAluno")
	public String perfil(Model model,@ModelAttribute("aluno") Aluno aluno) {
		Aluno a = service.getAlunoById(id);
		model.addAttribute("alunos", a);
		return "perfil-aluno";
	}
	@GetMapping("logOutAluno")
	public String logOut() {
		this.id=(long) 0;
		return "redirect:/";
	}
	@RequestMapping(path = "/createAluno", method = RequestMethod.POST)
	public String createOrUpdateEmployee( Aluno aluno) 
	{
		String url = "redirect:/";
		if(id!=0) url = "redirect:/homeLog";
		service.createOrUpdateAluno(aluno);
		return url;
	}
	@RequestMapping(path = {"/edit-aluno", "/edit-aluno/{id}"})
	public String editAlunoById(Model model, @PathVariable("id") Optional<Long> id){
		if (id.isPresent()) {
			Aluno entity = service.getAlunoById(id.get());
			model.addAttribute("aluno", entity);
		} else {
			model.addAttribute("aluno", new Aluno());
		}
		return "add-edit-aluno";
	}
	@RequestMapping(path = "/delete-aluno/{id}")
	public String deleteAlunoById(Model model, @PathVariable("id") Long id){
		service.deleteAlunoById(id);
		this.id =(long) 0;
		return "redirect:/";
	}

	@RequestMapping(path = "/logAluno", method = RequestMethod.POST)
	public String login(Aluno aluno) {
		var res = "erro";
		List<Aluno> list = service.getAllAlunos();
		
		List<Aluno> a =list.stream().filter((f) -> f.getEmail().equals(aluno.getEmail()) && f.getSenha().equals(aluno.getSenha())).collect(Collectors.toList());
		if(!a.isEmpty()) {
			res = "homeLog";
			id = a.get(0).getId();
		}
		return res;
	}

}
