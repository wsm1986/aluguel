package com.aluguel.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.dao.InquilinoDao;
import com.aluguel.models.Conta;
import com.aluguel.models.Despesas;
import com.aluguel.models.Inquilino;
import com.aluguel.repository.InquilinoRepository;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/inquilino")
public class InquilinoController {

	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	InquilinoRepository repository;
	
	

	@RequestMapping("/")
	private ModelAndView home() {
		ModelAndView mvn = new ModelAndView("index");
		return mvn;
	}

	@RequestMapping("/form")
	private ModelAndView cadastro() {
		ModelAndView mvn = new ModelAndView("inquilino/cadastroInquilino");
		Inquilino teste = new Inquilino();
		teste.setNome("wellington");
		teste.setNumeroCasa("1");
		teste.setDtInicioConverter("01/01/2015"); 
		teste.setDtFinalConverter("01/01/2016");
		Despesas despesas = new Despesas();
		Conta c = new Conta();
		c.setId(1l);
		despesas.setConta(c);
		despesas.setDtVenciomento(Calendar.getInstance());
		despesas.setIsStatus(true);
		despesas.setValor(new BigDecimal("550.00"));
		mvn.addObject("inquilino", teste);

		return mvn;
	}

	@RequestMapping("/novo")
	private ModelAndView novo(Inquilino inquilino) {
		ModelAndView mvn = new ModelAndView("despesas/listarDespesas");
		
		List<Despesas> lista = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Despesas despesas = new Despesas();
			//despesas.setInquilino(inquilino);
			Conta c = new Conta();
			despesas.setInquilino(inquilino);
			c.setId(2l);
			despesas.setConta(c);
			despesas.setDtVenciomento(Calendar.getInstance());
			despesas.setIsStatus(true);
			despesas.setValor(new BigDecimal("550.00"));
			lista.add(despesas);
		}
		inquilino.setListDespesas(lista);
		repository.save(inquilino);
		mvn.addObject("listDespesas", lista);

		return mvn;
	}

	@RequestMapping("/lista")
	private ModelAndView lista() {
		ModelAndView mvn = new ModelAndView("inquilino/listaInquilino");
		mvn.addObject("listaInquilino", repository.findAll());
		return mvn;
	}

	@RequestMapping("/data2")
	private ModelAndView getData() {

		ModelAndView mvn = new ModelAndView("inquilino/listaInquilino");
		String uri = "http://localhost:8080/cursos?campo=" + "well";
		String response = restTemplate.getForObject(uri, String.class);
		System.out.println(response);
		return mvn;
	}
}
