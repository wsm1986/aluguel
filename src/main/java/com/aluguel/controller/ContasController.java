package com.aluguel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.models.Conta;
import com.aluguel.models.MessageWeb;
import com.aluguel.repository.ContaRepository;

@Controller
@RequestMapping("/contas")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ContasController {

	List<Conta> li = new ArrayList<>();

	@Autowired
	ContaRepository dao;

	@RequestMapping("/form")
	private ModelAndView form(Conta conta) {
		ModelAndView mvn = new ModelAndView("contas/novo");
		mvn.addObject("comboContas", dao.findAll());
		return mvn;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	private ModelAndView adicionarConta(@Valid Conta conta, BindingResult result) {
		ModelAndView mvn = new ModelAndView("contas/novo");
		if (result.hasErrors()) {
			return form(conta);
		}
		dao.save(conta);
		mvn.addObject("comboContas", dao.findAll());
		mvn.addObject("contas", new Conta());
		mvn.addObject(MessageWeb.MESSAGE_ATTRIBUTE, MessageWeb.SUCCESS_ALTER);
		return mvn;
	}
	
}
