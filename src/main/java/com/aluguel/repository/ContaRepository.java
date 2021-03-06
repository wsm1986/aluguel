package com.aluguel.repository;

import org.springframework.data.repository.CrudRepository;

import com.aluguel.models.Conta;

public interface ContaRepository extends CrudRepository<Conta, Long>{
	Conta findByDescricao(String descricao);
	
	Conta findById(Long id); 
}
