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
import com.lab04.moedaEstudantil.model.Aluno;
import com.lab04.moedaEstudantil.service.AlunoService;
import com.lab04.moedaEstudantil.model.Professor;
import com.lab04.moedaEstudantil.service.ProfessorService;
import com.lab04.moedaEstudantil.model.Transacao;
import com.lab04.moedaEstudantil.service.TransacaoService;
@Controller
public class TransacaoController {
	@Autowired
	TransacaoService service;
	@Autowired
	AlunoController al;
	@Autowired
	ProfessorController pc;
	@GetMapping("envia-moedas")
	public String enviarMoedas(@ModelAttribute("transacao") Transacao transacao) {
		
		return "envia-moedas";
	}

	public String createTransacao(Transacao transacao) {
		service.createOrUpdateTransacao(transacao);
		al.setMoedas(transacao.getMoedas(),transacao.getAluno().getEmail());
		pc.setMoedas(transacao.getMoedas());
		return "redirect:/homeLP";
	}
	public List<Transacao> getContaAluno(Long id){
		List<Transacao> list = service.getAllTransacoes();
		List<Transacao> a =list.stream().filter(f -> f.getAluno().getId().equals(id)).collect(Collectors.toList());
		return a;
	}
	public List<Transacao> getContaProfessor(Long id){
		List<Transacao> list = service.getAllTransacoes();
		List<Transacao> a =list.stream().filter(f -> f.getProfessor().getId().equals(id)).collect(Collectors.toList());
		return a;
	}

}
