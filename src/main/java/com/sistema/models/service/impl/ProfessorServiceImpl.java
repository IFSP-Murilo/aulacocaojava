package com.sistema.models.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.models.model.Cidade;
import com.sistema.models.model.Professor;
import com.sistema.models.repository.CidadeRepository;
import com.sistema.models.repository.ProfessorRepository;
import com.sistema.models.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public Professor incluir(Professor professor) {
		Optional<Cidade> cidade = cidadeRepository.findById(professor.getCidade().getIdCidade());
	    professor.setCidade(cidade.get());
		return professorRepository.save(professor);
	}

	@Override
	public Professor alterar(Long id, Professor professor) {
	    
		Professor professorCadastrado = professorRepository.findById(id).get();

		professorCadastrado.setCidade(professor.getCidade());
		professorCadastrado.setCodProfessor(professor.getCodProfessor());
		professorCadastrado.setNomeProfessor(professor.getNomeProfessor());
	
		return professorRepository.save(professorCadastrado);
	}
	
	@Override
	public void excluir(Long id) {
		professorRepository.deleteById(id);
		
	}

	@Override
	public Professor consultarPorId(Long id) {
		return professorRepository.findById(id).get();
	}

	@Override
	public List<Professor> lista() {
		return professorRepository.findAll();
	}

	@Override
	public List<Professor> lista(String keyword) {
		return professorRepository.findAllByFields(keyword);
	}

	

}
