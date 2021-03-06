package com.lab04.moedaEstudantil.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab04.moedaEstudantil.model.Empresa;
import com.lab04.moedaEstudantil.model.Vantagem;
import com.lab04.moedaEstudantil.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	EmpresaRepository repository;
	
	public List<Empresa> getAllEmpresas(){
		List<Empresa> result = (List<Empresa>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Empresa>();
		}
	}
	
	public Empresa getAEmpresaById(Long id){
		Optional<Empresa> empresa = repository.findById(id);
		
		if(empresa.isPresent()) return empresa.get();
		else return null;
		
	}
	
	public Empresa createOrUpdateEmpresa(Empresa al){
		if(al.getId()  == null){
			al = repository.save(al);
			
			return al;
		} 
		else {
			Optional<Empresa> empresa = repository.findById(al.getId());
			
			if(empresa.isPresent()){
				Empresa newEmpresa = empresa.get();
				newEmpresa.setNome(al.getNome());
				newEmpresa.setCnpj(al.getCnpj());
				newEmpresa.setEmail(al.getEmail());
				newEmpresa.setSenha(al.getSenha());
				newEmpresa = repository.save(newEmpresa);
				
				return newEmpresa;
			} else {
				al = repository.save(al);
				
				return al;
			}
		}
	} 
	public Empresa addVantagem(Vantagem v, Empresa al){
		Optional<Empresa> empresa = repository.findById(al.getId());
		if(empresa.isPresent()) {
			Empresa ne = empresa.get();
			ne.getVantagens().add(v);
			al = repository.save(ne);
			return al;
		}
		else return null;
	}
	public Empresa deleteVantagem(Vantagem v, Empresa al){
		Optional<Empresa> empresa = repository.findById(al.getId());
		if(empresa.isPresent()) {
			Empresa ne = empresa.get();
			ne.getVantagens().remove(v);
			al = repository.save(ne);
			return al;
		}
		else return null;
	}
	
	public void deleteEmpresaById(Long id) {
		Optional<Empresa> empresa = repository.findById(id);
		
		if(empresa.isPresent()) 
		{
			repository.deleteById(id);
		} 
	} 
	
}

