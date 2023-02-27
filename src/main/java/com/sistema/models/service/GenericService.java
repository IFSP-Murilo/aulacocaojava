package com.sistema.models.service;

import java.util.List;

public interface GenericService<T,ID> {

	  public T incluir(T entity );
	
	  public T alterar(ID id, T entity);
	  
	  public void excluir(ID id);
	  
	  public T consultarPorId(ID id);
	  
	  public List<T> lista();
	  
	  public List<T> lista(String keyword);
	
}
