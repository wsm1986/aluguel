package com.aluguel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aluguel.models.Inquilino;

public interface InquilinoRepository extends CrudRepository<Inquilino, Long> {

}
