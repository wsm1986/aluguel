package com.aluguel.repository;

import org.springframework.data.repository.CrudRepository;

import com.aluguel.models.AInquilino;

public interface InquilinoRepository extends CrudRepository<AInquilino, Long>{
	AInquilino findByNome(String nome);
	AInquilino findById(Long id);
	
}

