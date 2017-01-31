package com.aluguel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluguel.models.Inquilino;
import com.aluguel.repository.InquilinoRepository;

@Service
public class InquilinoDao {

	@Autowired
	public InquilinoRepository rep;
	
	public List<Inquilino> list(){
		return (List<Inquilino>) rep.findAll();
	}
	
	public void save(Inquilino inquilino){
		save(inquilino);
	}
	
}
