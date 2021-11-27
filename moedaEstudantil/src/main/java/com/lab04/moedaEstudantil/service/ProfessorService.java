package com.lab04.moedaEstudantil.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab04.moedaEstudantil.model.Empresa;
import com.lab04.moedaEstudantil.model.Professor;
import com.lab04.moedaEstudantil.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository repository;
	
	public List<Professor> getAllProfessores(){
		List<Professor> result = (List<Professor>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Professor>();
		}
	}
	
	public Professor getProfessorById(Long id){
		Optional<Professor> empresa = repository.findById(id);
		
		if(empresa.isPresent()) return empresa.get();
		else return null;
		
	}
	public Professor createOrUpdateEmpresa(Professor al,int quant){
		if(al.getId()  == null){
			al = repository.save(al);
			
			return al;
		} 
		else {
			Optional<Professor> p = repository.findById(al.getId());
			
			if(p.isPresent()){
				Professor newE = p.get();
				newE.setNome(al.getNome());
				newE.setCpf(al.getCpf());
				newE.setEmail(al.getEmail());
				newE.setSenha(al.getSenha());
				newE.setDepartamento(al.getDepartamento());
				newE.setUniversidade(al.getUniversidade());
				newE.setMoedas(al.getMoedas() - quant);
				newE= repository.save(newE);
				
				return newE;
			} else {
				al = repository.save(al);
				
				return al;
			}
		}
	} 
	
}