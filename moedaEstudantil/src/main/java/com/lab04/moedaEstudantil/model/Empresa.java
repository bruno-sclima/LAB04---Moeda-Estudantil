package com.lab04.moedaEstudantil.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name="Empresa")
public class Empresa {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(name="nome")
	private String nome;
	 @Column(name="cnpj")
	private String cnpj;
	 @ElementCollection
	 @Column(name="vantagens")
	private List<Vantagem> vantagens;
	 
	 
	public Empresa(Long id, String nome, String cnpj) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.vantagens = new ArrayList<>();
	}
	
	

}
