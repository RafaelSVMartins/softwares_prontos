package com.fatec.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Documento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar recebido;
	@Enumerated(EnumType.STRING)
	private StatusDocumento status;
	
	private String anterior_2meses;
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	
	public String getAnterior_2meses() {
		return anterior_2meses;
	}


	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Calendar getRecebido() {
		return recebido;
	}
	
	public void setRecebido(Calendar recebido) {
		this.recebido = recebido;
	}
	public StatusDocumento getStatus() {
		return status;
	}
	public void setStatus(StatusDocumento status) {
		this.status = status;
	}
	
	public boolean isPendente() {
		return StatusDocumento.PENDENTE.equals(this.status);
	}

	public void setAnterior_2meses(String testeCalendar) {
		// TODO Auto-generated method stub
			this.anterior_2meses = testeCalendar;
	}

		
}
