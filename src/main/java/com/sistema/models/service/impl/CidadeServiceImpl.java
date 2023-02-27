package com.sistema.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.models.model.Cidade;
import com.sistema.models.repository.CidadeRepository;
import com.sistema.models.service.CidadeService;

@Service
@Transactional
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@Override
	public Cidade incluir(Cidade cidade) {
	    return cidadeRepository.save(cidade); 
	}

	@Override
	public Cidade alterar(Long id, Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	@Override
	public void excluir(Long id) {
		cidadeRepository.deleteById(id);
		
	}
    
	
	@Transactional(readOnly = true)
	@Override
	public Cidade consultarPorId(Long id) {
		return cidadeRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cidade> lista() {
		return cidadeRepository.findAll();
	}

	@Override
	public List<Cidade> lista(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
