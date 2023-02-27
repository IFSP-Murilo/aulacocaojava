package com.sistema.models.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ALUNO")
public class Aluno {

	private Long idAluno;
	private String codAluno;
	private String nomeAluno;
	private Integer idadeAluno;
	private Cidade cidade;

	public Aluno() {

	}

	public Aluno(Long idAluno, String codAluno, String nomeAluno, Integer idadeAluno, Cidade cidade) {
		this.idAluno = idAluno;
		this.codAluno = codAluno;
		this.nomeAluno = nomeAluno;
		this.idadeAluno = idadeAluno;
		this.cidade = cidade;
	}

	@Id
	@Column(name="ID_ALUNO")
	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	@Column(name="COD_ALUNO")
	public String getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}

	@Column(name="NOME_ALUNO")
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	@Column(name="IDADE")
	public Integer getIdadeAluno() {
		return idadeAluno;
	}

	public void setIdadeAluno(Integer idadeAluno) {
		this.idadeAluno = idadeAluno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CIDADE")
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
