package com.sistema.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.models.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	@Query(value="SELECT p FROM Professor p "
			+ "WHERE p.idProfessor LIKE(CONCAT('%',:keyword,'%')) "
			+ "OR p.codProfessor LIKE(CONCAT('%',:keyword,'%')) "
			+ "OR p.nomeProfessor LIKE(CONCAT('%',:keyword,'%')) "
			+ "OR p.cidade.nomeCidade LIKE(CONCAT('%',:keyword,'%'))")
	List<Professor> findAllByFields(@Param("keyword") String keyword);

}
