package com.aluguel.repository;

import org.springframework.data.repository.CrudRepository;

import com.aluguel.models.Inquilino2;

public interface InquilinoRepository extends CrudRepository<Inquilino2, Long>{
	Inquilino2 findByNome(String nome);
	Inquilino2 findById(Long id);
	
}

