package com.lab04.moedaEstudantil.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@NoArgsConstructor
@Entity
@Table(name="Professor")
public class Professor {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(name="nome")
	private String nome;
	@Column(name="cpf")
	private String cpf;
	 @Column(name="senha")
	private String senha;
	@Column(name="universidade")
	private String universidade;
	@Column(name="email")
	private String email;
	 @Column(name="departamento")
	private String departamento;
	 @Column(name="moedas")
	private int moedas;
	public Professor(String nome, String cpf, String senha, String universidade, String email,String departamento,int moedas) {
		
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSenha(senha);
		this.setUniversidade(universidade);
		this.setEmail(email);
		this.setDepartamento(departamento);
		this.setMoedas(moedas);
	}
	 
}
