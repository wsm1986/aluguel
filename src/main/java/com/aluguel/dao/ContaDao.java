package com.aluguel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluguel.models.Conta;
import com.aluguel.repository.ContaRepository;

@Service
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
