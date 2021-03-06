package com.lab04.moedaEstudantil.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lab04.moedaEstudantil.model.Aluno;
import com.lab04.moedaEstudantil.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AlunoService {
	@Autowired
	AlunoRepository repository;
	
	public List<Aluno> getAllAlunos(){
		List<Aluno> result = (List<Aluno>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Aluno>();
		}
	}
	
	public Aluno getAlunoById(Long id){
		Optional<Aluno> aluno = repository.findById(id);
		
		if(aluno.isPresent()) {
			return aluno.get();
			
		} else return null;
		
	}
	
	public Aluno createOrUpdateAluno(Aluno al){
		if(al.getId()  == null){
			al = repository.save(al);
			
			return al;
		} 
		else {
			Optional<Aluno> aluno = repository.findById(al.getId());
			
			if(aluno.isPresent()){
				Aluno newAluno = aluno.get();
				newAluno.setEmail(al.getEmail());
				newAluno.setNome(al.getNome());
				newAluno.setCpf(al.getCpf());
				newAluno.setCurso(al.getCurso());
				newAluno.setNomeUniv(al.getNomeUniv());
				newAluno.setSenha(al.getSenha());
				newAluno.setMoedas(al.getMoedas());
				newAluno = repository.save(newAluno);
				
				return newAluno;
			} else {
				al = repository.save(al);
				
				return al;
			}
		}
	} 
	public Aluno updateMoedas(Long id,int quant){
		List<Aluno> result = (List<Aluno>) repository.findAll();
		List<Aluno> aux =result.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
		Aluno a = aux.get(0);
		a.setMoedas(quant + a.getMoedas());
		a = repository.save(a);
		return a;
	} 
	
	public void deleteAlunoById(Long id) {
		Optional<Aluno> aluno = repository.findById(id);
		
		if(aluno.isPresent()) 
		{
			repository.deleteById(id);
		} 
	} 

}
