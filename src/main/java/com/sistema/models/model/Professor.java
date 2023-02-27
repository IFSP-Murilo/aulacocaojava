package com.sistema.models.model;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PROFESSOR")
public class Professor {

	
	private Long    idProfessor;
	private String  codProfessor;
	private String  nomeProfessor;
	private Cidade  cidade;
	
    
	private List<Disciplina> listaDisciplina;
	
	public Professor() {
		
	}

	public Professor(Long idProfessor, String codProfessor, String nomeProfessor, Cidade cidade) {
		this.idProfessor = idProfessor;
		this.codProfessor = codProfessor;
		this.nomeProfessor = nomeProfessor;
		this.cidade = cidade;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFESSOR_SEQ")
	@SequenceGenerator(sequenceName = "SEQUENCE_PROFESSOR",initialValue = 1,allocationSize = 1, name="PROFESSOR_SEQ")
	@Column(name = "ID_PROFESSOR")
	public Long getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	

	@NotBlank(message = "O código do professor deve ser informado")
    @NotNull(message = "O campo código do professor não pode ser nulo ")
	@Column(name = "COD_PROFESSOR")
	public String getCodProfessor() {
		return codProfessor;
	}

	public void setCodProfessor(String codProfessor) {
		this.codProfessor = codProfessor;
	}

	@NotBlank(message = "O nome do professor deve ser informado")
	@Column(name = "NOME_PROFESSOR")
	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	@NotNull(message = "A cidade deve ser selecionada")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CIDADE")
	public Cidade getCidade() {
		return cidade;
	}



	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "professor",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Disciplina> getListaDisciplina() {
		return listaDisciplina;
	}
     
	
	
	public void setListaDisciplina(List<Disciplina> listaDisciplina) {
		this.listaDisciplina = listaDisciplina;
	}

	@Override
	public String toString() {
		return "Professor [idProfessor=" + idProfessor + ", codProfessor=" + codProfessor + ", nomeProfessor="
				+ nomeProfessor + ", cidade=" + cidade + "]";
	}
	
	
    
}
