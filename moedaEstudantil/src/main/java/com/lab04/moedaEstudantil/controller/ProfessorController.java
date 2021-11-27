package com.lab04.moedaEstudantil.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lab04.moedaEstudantil.controller.AlunoController;
import com.lab04.moedaEstudantil.controller.TransacaoController;
import com.lab04.moedaEstudantil.model.Aluno;
import com.lab04.moedaEstudantil.model.Professor;
import com.lab04.moedaEstudantil.model.Transacao;
import com.lab04.moedaEstudantil.service.ProfessorService;
@Controller
public class ProfessorController {

	@Autowired
	ProfessorService service;
	protected Long id = (long) 0;
	@Autowired
	AlunoController al;
	@Autowired
	TransacaoController tc;
	@GetMapping("list-professores")
	public String getAllAlunos(Model model){
		List<Professor> list = service.getAllProfessores();

		model.addAttribute("professores", list);
		return "list-professores";
	}
	@GetMapping("logOutProfessor")
	public String logOut() {
		this.id=(long) 0;
		return "redirect:/";
	}
	@GetMapping("login-professor")
	public String pagLogin(@ModelAttribute("professor") Professor professor) {
		
		return "login-professor";
	}
	@RequestMapping(path = "/logProf", method = RequestMethod.POST)
	public String login(Professor prof) {
		var res = "erro";
		List<Professor> list = service.getAllProfessores();
		
		List<Professor> a =list.stream().filter((f) -> f.getEmail().equals(prof.getEmail()) && f.getSenha().equals(prof.getSenha())).collect(Collectors.toList());
		if(!a.isEmpty()) {
			res = "homeLP";
			id = a.get(0).getId();
		}
		return res;
	}
	@GetMapping("homeLP")
	public String homeLog() {
		return "homeLP";
	}
	@GetMapping("home2")
	public String home() {
		String url = "index";
		if(id!=0) url = "homeLP";
		return url;
	}
	@RequestMapping(path = "/createTransacao", method = RequestMethod.POST)
	public String createOrUpdateEmployee( Transacao transacao) 
	{
		Professor p = service.getProfessorById(this.id);
		transacao.setProfessor(p);
		Aluno a = al.alunoByEmail(transacao.getAux());
		if(a!= null) {
			transacao.setAluno(a);
		}
		String redirect = tc.createTransacao(transacao);
		return redirect;
		
	}
	public void setMoedas(int quant) {
		Professor p = service.getProfessorById(this.id);
		if(quant <= p.getMoedas()) service.createOrUpdateEmpresa(p, quant);
		
	}
	@GetMapping("conta-professor")
	public String getConta(Model model){
		List<Transacao> list = tc.getContaProfessor(this.id);
		Professor professor = service.getProfessorById(this.id);
		model.addAttribute("saldo",professor.getMoedas());
		model.addAttribute("transacoes", list);
		return "conta-professor";
	}
}
