package com.aluguel.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluguel.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
