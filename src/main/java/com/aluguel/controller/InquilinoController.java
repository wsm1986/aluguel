package com.aluguel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.models.Inquilino;

@Controller
public class InquilinoController {

	@RequestMapping("/")
	private ModelAndView home(){
		ModelAndView mvn = new ModelAndView("index");
		return mvn;
	}
	
	@RequestMapping("/cadastro")
	private ModelAndView cadastro(){
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
	private ModelAndView lista(){
		ModelAndView mvn = new ModelAndView("listaInquilino");
		return mvn;
	}
}
