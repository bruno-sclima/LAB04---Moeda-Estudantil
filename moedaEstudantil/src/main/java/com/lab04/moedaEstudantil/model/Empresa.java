package com.lab04.moedaEstudantil.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@NoArgsConstructor
@Table(name="Empresa")
public class Empresa {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(name="nome")
	private String nome;
	 @Column(name="cnpj")
	private String cnpj;
	 @Column(name="email")
	private String email;
	 @Column(name="senha")
	private String senha;
	 @ElementCollection
	 @Column(name="vantagens")
	private List<Vantagem> vantagens;
	 
	 
	public Empresa(Long id, String nome, String cnpj, String email, String senha) {
		this.setId(id);
		this.setNome(nome);
		this.setCnpj(cnpj);
		this.setSenha(senha);
		this.setVantagens(new ArrayList<>());
		this.setEmail(email);
	}
	
	

}
