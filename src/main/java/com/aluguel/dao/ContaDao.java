package com.aluguel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aluguel.models.Conta;
import com.aluguel.repository.ContaRepository;

public class ContaDao {

	@Autowired 
	ContaRepository repository;
	
	public void save(Conta c){
		save(c);
	}
	
	public List<Conta> findAll(){
		return findAll();
	}
	
	
}
