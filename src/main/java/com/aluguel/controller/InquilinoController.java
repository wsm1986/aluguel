package com.aluguel.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import com.aluguel.models.MessageWeb;
import com.aluguel.repository.ContaRepository;
import com.aluguel.repository.InquilinoRepository;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/inquilino")
public class InquilinoController {

	@Autowired
	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	InquilinoRepository inquilinoRepository;

	@Autowired
	ContaRepository contaRepository;

	private final String aluguel = "Aluguel";

	@RequestMapping("/")
	private ModelAndView home() {
		ModelAndView mvn = new ModelAndView("index");
		return mvn;
	}

	@RequestMapping("/form")
	private ModelAndView cadastro(Inquilino inquilino) {
		ModelAndView mvn = new ModelAndView("inquilino/novo");
		/*List<Inquilino> list = (List<Inquilino>) inquilinoRepository.findAll();
		for (Inquilino aux : list) {
			inquilinoRepository.delete(aux);
		}*/
		return mvn;
	}

	@RequestMapping("/novo")
	private ModelAndView novo(Inquilino inquilino) {
		List<Despesas> lista = new ArrayList<Despesas>();
		Despesas despesas;
		Conta conta = contaRepository.findByDescricao(aluguel);

		while (!inquilino.getDtInicioContrato().getTime().after(inquilino.getDtFinalContrato().getTime())) {
			Calendar calendar = new GregorianCalendar(inquilino.getDtInicioContrato().get(Calendar.YEAR),
					inquilino.getDtInicioContrato().get(Calendar.MONTH),
					inquilino.getDtInicioContrato().get(Calendar.DAY_OF_WEEK));
			despesas = new Despesas();
			despesas.setInquilino(inquilino);
			despesas.setConta(conta);
			despesas.setDtVenciomento(calendar);
			despesas.setIsStatus(Boolean.FALSE);
			despesas.setValor(inquilino.getValorContrato());
			lista.add(despesas);
			inquilino.getDtInicioContrato().add(Calendar.MONTH, 1);

		}

		inquilino.setListDespesas(lista);
		inquilinoRepository.save(inquilino);
		//ModelAndView mav = new ModelAndView("redirect:form");
		ModelAndView mvn = new ModelAndView("inquilino/novo");
		mvn.addObject(MessageWeb.MESSAGE_ATTRIBUTE, MessageWeb.SUCCESS_ALTER);
		return mvn;
	}

	@RequestMapping("/lista")
	private ModelAndView inquilinos() {
		ModelAndView mvn = new ModelAndView("inquilino/lista");
		mvn.addObject("listaInquilino", inquilinoRepository.findAll());
		return mvn;
	}

	@RequestMapping("/data2")
	private ModelAndView getData() {

		ModelAndView mvn = new ModelAndView("inquilino/lista");
		String uri = "http://localhost:8080/cursos?campo=" + "well";
		String response = restTemplate.getForObject(uri, String.class);
		System.out.println(response);
		return mvn;
	}
}
