package com.lab04.moedaEstudantil.service;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import com.lab04.moedaEstudantil.model.Aluno;
import com.lab04.moedaEstudantil.model.Vantagem;
import com.lab04.moedaEstudantil.repository.AlunoRepository;
import com.lab04.moedaEstudantil.repository.VantagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class VantagemService {
	@Autowired
	VantagemRepository repository;
	
	public List<Vantagem> getAllVantagens(){
		List<Vantagem> result = (List<Vantagem>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Vantagem>();
		}
	}
	
	public Vantagem getVantagemById(Long id){
		Optional<Vantagem> van = repository.findById(id);
		
		if(van.isPresent()) {
			return van.get();
			
		} else return null;
		
	}
	
	public Vantagem createOrUpdateVantagem(Vantagem al){
		if(al.getId()  == null){
			al = repository.save(al);
			
			return al;
		} 
		else {
			Optional<Vantagem> van = repository.findById(al.getId());
			
			if(van.isPresent()){
				Vantagem newVan = van.get();
				newVan.setDescricao(al.getDescricao());
				newVan.setTipo(al.getTipo());
				newVan.setValor(al.getValor());
			
				newVan = repository.save(newVan);
				
				return newVan;
			} else {
				al = repository.save(al);
				
				return al;
			}
		}
	} 
	
	public void deleteVantagemById(Long id) {
		Optional<Vantagem> van = repository.findById(id);
		
		if(van.isPresent()) 
		{
			repository.deleteById(id);
		} 
	} 

}
