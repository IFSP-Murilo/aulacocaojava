package com.sistema.models.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DISCIPLINA")
public class Disciplina {

	private Long      idDisciplina;
	private String    codDisciplina;
	private String    nomeDisciplina;
	private Professor professor;
    private List<Avaliacao> listaAvaliacao;

	public Disciplina() {

	}

	public Disciplina(Long idDisciplina, String codDisciplina, String nomeDisciplina, Professor professor) {
		this.idDisciplina = idDisciplina;
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.professor = professor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISCIPLINA_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCE_DISCIPLINA",initialValue = 1,
	                   allocationSize = 1, name="DISCIPLINA_SEQ")
	@Column(name="ID_DISCIPLINA")
	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	@Column(name="COD_DISCIPLINA")
	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
  
	@Column(name="NOME_DISCIPLINA")
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROFESSOR",referencedColumnName = "ID_PROFESSOR")
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@OneToMany(mappedBy = "disciplina")
	public List<Avaliacao> getListaAvaliacao() {
		return listaAvaliacao;
	}

	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
	}

	
	

	
}
