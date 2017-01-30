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

import com.aluguel.models.Conta;
import com.aluguel.models.Despesas;
import com.aluguel.models.Inquilino;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/inquilino")
public class InquilinoController {

	@Autowired
	RestTemplate restTemplate = new RestTemplate();

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
		teste.setDtFinalContrato(Calendar.getInstance());
		teste.setDtInicioContrato(Calendar.getInstance());
		mvn.addObject("inquilino", teste);

		return mvn;
	}

	@RequestMapping("/novo")
	private ModelAndView novo(Inquilino inquilino) {
		ModelAndView mvn = new ModelAndView("despesas/listarDespesas");
		List<Despesas> lista = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			String nome = inquilino.getNome() + " - " + i;
			inquilino.setNome(nome);
			Despesas despesas = new Despesas();
			despesas.setInquilino(inquilino);
			despesas.setConta(new Conta("Aluguel"));
			despesas.setDtVenciomento(Calendar.getInstance());
			despesas.setIsStatus(true);
			despesas.setValor(new BigDecimal("550.00"));
			lista.add(despesas);
		}
		mvn.addObject("listDespesas", lista);

		return mvn;
	}

	@RequestMapping("/lista")
	private ModelAndView lista() {
		ModelAndView mvn = new ModelAndView("inquilino/listaInquilino");
		List<Inquilino> lista = new ArrayList<Inquilino>();
		for (int i = 0; i < 20; i++) {
			Inquilino inquilino = new Inquilino();
			inquilino.setNome("wellington" + i);
			inquilino.setNumeroCasa("1" + i);
			inquilino.setDtFinalContrato(Calendar.getInstance());
			inquilino.setDtInicioContrato(Calendar.getInstance());
			lista.add(inquilino);
		}
		mvn.addObject("listaInquilino", lista);
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
