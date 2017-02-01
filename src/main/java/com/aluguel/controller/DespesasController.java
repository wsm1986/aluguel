package com.aluguel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.repository.DespesasRepository;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("despesas")
public class DespesasController {

	@Autowired
	DespesasRepository repository;

	@RequestMapping("/pesquisa")
	private ModelAndView lista() {
		ModelAndView mvn = new ModelAndView("despesas/listarDespesas");

		mvn.addObject("listDespesas", repository.findAll());
		return mvn;
	}

}
