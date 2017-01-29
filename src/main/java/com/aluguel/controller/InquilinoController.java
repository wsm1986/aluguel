package com.aluguel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping("/cadastro")
	private ModelAndView cadastro() {
		ModelAndView mvn = new ModelAndView("cadastroInquilino");
		Inquilino teste = new Inquilino();
		teste.setNome("wellington");
		teste.setNumeroCasa("1");
		teste.setDtFinalContrato("01/01/2018");
		teste.setDtInicioContrato("01/01/2015");
		mvn.addObject("inquilino", teste);

		return mvn;
	}

	@RequestMapping("/lista")
	private ModelAndView lista() {
		ModelAndView mvn = new ModelAndView("listaInquilino");
		List<Inquilino> lista = new ArrayList<Inquilino>();
		for (int i = 0; i < 20; i++) {
			Inquilino inquilino = new Inquilino();
			inquilino.setNome("wellington" + i);
			inquilino.setNumeroCasa("1" + i);
			inquilino.setDtFinalContrato("01/01/2018");
			inquilino.setDtInicioContrato("01/01/2015");
			lista.add(inquilino);
		}
		mvn.addObject("listaInquilino", lista);
		return mvn;
	}

	@RequestMapping("/data2")
	private ModelAndView getData() {

		ModelAndView mvn = new ModelAndView("listaInquilino");
		String uri = "http://localhost:8080/cursos?campo=" + "well";
		String response = restTemplate.getForObject(uri, String.class);
		System.out.println(response);
		return mvn;
	}
}
