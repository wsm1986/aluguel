package com.aluguel.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aluguel.models.Despesas;

public interface DespesasRepository extends CrudRepository<Despesas, Long>{
	
	Despesas findById(Long id);
	
	List<Despesas> findByDtVenciomentoBeforeAndIsStatus(Calendar dt, Boolean status);
}
