package com.lab04.moedaEstudantil.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Data
@Entity
@NoArgsConstructor
@Table(name="Vantagem")
public class Vantagem {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(name="tipo")
	private String tipo;
	 @Column(name="valor")
	private double valor;
	 @Column(name="descricao")
	private String descricao;
	/*
	 @Column(name="empresa")
	private Empresa empresa;*/
	
	public Vantagem(String tipo, double valor, Empresa empresa, String descricao) {
		
		this.setTipo(tipo);
		this.setValor(valor);
		//this.setEmpresa(empresa);
		this.setDescricao(descricao);
	}
	

}
