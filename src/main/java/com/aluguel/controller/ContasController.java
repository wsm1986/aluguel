package com.aluguel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.models.Conta;

@Controller
@RequestMapping("/contas")
public class ContasController {


	List<Conta> li = new ArrayList<>();

	@RequestMapping("/form")
	private ModelAndView cadastro() {
		ModelAndView mvn = new ModelAndView("contas/cadastro");
		mvn.addObject("contas", new Conta());
		li.add(new Conta("Conta 1"));
		li.add(new Conta("Conta 2"));
		mvn.addObject("comboContas", li);
		return mvn;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	private ModelAndView adicionarConta(Conta conta) {
		ModelAndView mvn = new ModelAndView("contas/cadastro");
		mvn.addObject("contas", new Conta());
		li.add(conta);
		mvn.addObject("comboContas", li);
		return mvn;
	}


	
}
