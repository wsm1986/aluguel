package com.aluguel.repository;

import org.springframework.data.repository.CrudRepository;

import com.aluguel.models.Inquilino;

public interface InquilinoRepository extends CrudRepository<Inquilino, Long>{
	Inquilino findByNome(String nome);
	Inquilino findById(Long id);
	
}

