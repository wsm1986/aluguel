package com.aluguel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.models.Conta;
import com.aluguel.models.MessageWeb;
import com.aluguel.repository.ContaRepository;

@Controller
@RequestMapping("/contas")
public class ContasController {

	List<Conta> li = new ArrayList<>();

	@Autowired
	ContaRepository dao;

	@RequestMapping("/form")
	private ModelAndView form() {
		ModelAndView mvn = new ModelAndView("contas/novo");
		mvn.addObject("contas", new Conta());
		mvn.addObject("comboContas", dao.findAll());
		mvn.addObject(MessageWeb.MESSAGE_ATTRIBUTE, MessageWeb.SUCCESS_ALTER);
		return mvn;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	private ModelAndView adicionarConta(Conta conta) {
		dao.save(conta);
		return form();
	}

}
