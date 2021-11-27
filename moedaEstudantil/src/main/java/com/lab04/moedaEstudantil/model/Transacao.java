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
@NoArgsConstructor
@Entity
@Table(name="Transacao")
public class Transacao {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="motivo")
	private String motivo;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private Professor professor;
	@Column(name="moedas")
	private int moedas;
	@Column(name="aux")
	private String aux;
	public Transacao(String motivo, Aluno aluno, Professor professor, int moedas) {
		this.setMotivo(motivo);
		this.setAluno(aluno);
		this.setProfessor(professor);
		this.setMoedas(moedas);
	}
	

}
