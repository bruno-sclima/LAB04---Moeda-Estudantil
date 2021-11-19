package com.lab04.moedaEstudantil.model;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name="Universidade")
public class InstituicaoDeEnsino {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(name="nome")
	private String nome;
	 
	 
	public InstituicaoDeEnsino(String nome) {
		
		this.setNome(nome);
	}
}
