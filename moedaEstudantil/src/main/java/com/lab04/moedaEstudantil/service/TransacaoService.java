package com.lab04.moedaEstudantil.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.lab04.moedaEstudantil.model.Transacao;
import com.lab04.moedaEstudantil.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TransacaoService {
	@Autowired
	TransacaoRepository repository;
	
	public List<Transacao> getAllTransacoes(){
		List<Transacao> result = (List<Transacao>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Transacao>();
		}
	}
	
	public Transacao getTransacaoById(Long id){
		Optional<Transacao> t = repository.findById(id);
		
		if(t.isPresent()) {
			return t.get();
			
		} else return null;
		
	}
	
	public Transacao createOrUpdateTransacao(Transacao al){
		if(al.getId()  == null){
			al = repository.save(al);
			
			return al;
		} 
		else {
			Optional<Transacao> t = repository.findById(al.getId());
			
			if(t.isPresent()){
				Transacao newTrans = t.get();
				newTrans.setMotivo(al.getMotivo());
				
				newTrans.setAluno(al.getAluno());
				newTrans.setProfessor(al.getProfessor());
				newTrans.setMoedas(al.getMoedas());
				newTrans = repository.save(newTrans);
				
				return newTrans;
			} else {
				al = repository.save(al);
				
				return al;
			}
		}
	} 

}
