package com.lab04.moedaEstudantil.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@NoArgsConstructor
@Entity
@Table(name="Aluno")
public class Aluno {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	 @Column(name="nome")
	private String nome;
	 @Column(name="cpf")
	private String cpf;
	 @Column(name="senha")
	private String senha;
	 @Column(name="nomeUniv")
	private String nomeUniv;
	 @Column(name="email")
	private String email;
	 @Column(name="curso")
	private String curso;
	 
	 
	public Aluno(String nome, String cpf, String senha, String univ, String email, String curso) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.setNomeUniv(univ);
		this.setEmail(email);
		this.setCurso(curso);
	}

	
	
	

}
