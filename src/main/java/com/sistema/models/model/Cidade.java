package com.sistema.models.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CIDADE")
public class Cidade {

	private Long idCidade;
	private String codCidade;
	private String nomeCidade;
	
	private Set<Professor> listaProfessor;
	private Set<Aluno> listaAluno;
	

	public Cidade() {
	}

	public Cidade(Long idCidade, String codCidade, String nomeCidade) {
		this.idCidade = idCidade;
		this.codCidade = codCidade;
		this.nomeCidade = nomeCidade;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CIDADE_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCE_CIDADE",initialValue = 1,allocationSize = 1, name="CIDADE_SEQ")
	@Column(name="ID_CIDADE")
	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	@Column(name="COD_CIDADE")
	public String getCodCidade() {
		return codCidade;
	}

	public void setCodCidade(String codCidade) {
		this.codCidade = codCidade;
	}

	@Column(name="NOME_CIDADE")
	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
	public Set<Professor> getListaProfessor() {
		return listaProfessor;
	}

	public void setListaProfessor(Set<Professor> listaProfessor) {
		this.listaProfessor = listaProfessor;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "cidade",fetch = FetchType.LAZY)
	public Set<Aluno> getListaAluno() {
		return listaAluno;
	}

	public void setListaAluno(Set<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}

}
