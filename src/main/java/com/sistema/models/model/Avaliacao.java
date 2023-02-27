package com.sistema.models.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

	private Long       idAvaliacao;
	private String     codAvaliacao;
	private String     descricaoAvaliacao;
	private Disciplina disciplina;
	
	
	public Avaliacao() {
		
	}


	public Avaliacao(Long idAvaliacao, String codAvaliacao, String descricaoAvaliacao, Disciplina disciplina) {
		this.idAvaliacao = idAvaliacao;
		this.codAvaliacao = codAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
		this.disciplina = disciplina;
	}


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AVALIACAO_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCE_AVALIACAO",initialValue = 1,allocationSize = 1, name="AVALIACAO_SEQ")
    @Column(name = "ID_AVALIACAO")
	public Long getIdAvaliacao() {
		return idAvaliacao;
	}



	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}


	@Column(name="COD_AVALIACAO")
	public String getCodAvaliacao() {
		return codAvaliacao;
	}



	public void setCodAvaliacao(String codAvaliacao) {
		this.codAvaliacao = codAvaliacao;
	}


	@Column(name="DESCRICAO")
	public String getDescricaoAvaliacao() {
		return descricaoAvaliacao;
	}



	public void setDescricaoAvaliacao(String descricaoAvaliacao) {
		this.descricaoAvaliacao = descricaoAvaliacao;
	}


    @ManyToOne
    @JoinColumn(name="ID_DISCIPLINA")
	public Disciplina getDisciplina() {
		return disciplina;
	}



	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
	
}
