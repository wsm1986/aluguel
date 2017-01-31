package com.aluguel.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.models.Conta;
import com.aluguel.models.Despesas;
import com.aluguel.models.Inquilino;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("despesas")
public class DespesasController {

	@RequestMapping("/pesquisa")
	private ModelAndView lista() {
		ModelAndView mvn = new ModelAndView("despesas/listarDespesas");
		List<Despesas> lista= new ArrayList<>();
		Despesas despesas = new Despesas();
		//despesas.setInquilino(new Inquilino("CASA 1","WELL",Calendar.getInstance(), Calendar.getInstance()));
		despesas.setConta(new Conta("LUZ"));
		despesas.setDtVenciomento(Calendar.getInstance());
		despesas.setIsStatus(true);
		despesas.setValor(new BigDecimal("20.00"));
		lista.add(despesas);
		mvn.addObject("listDespesas", lista);
		return mvn;
	}
	
}
