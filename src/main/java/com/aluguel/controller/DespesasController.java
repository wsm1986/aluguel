package com.aluguel.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.aluguel.models.Despesas;
import com.aluguel.models.AInquilino;
import com.aluguel.repository.ContaRepository;
import com.aluguel.repository.DespesasRepository;
import com.aluguel.repository.InquilinoRepository;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/despesas")
public class DespesasController {

	@Autowired
	DespesasRepository repository;
	
	@Autowired
	InquilinoRepository inquilinoRepository;
	
	@Autowired
	ContaRepository dao;

	@RequestMapping("/form")
	private ModelAndView cadastro(Despesas despesas) {
		ModelAndView mvn = new ModelAndView("despesas/novo");
		mvn.addObject("inquilinos", inquilinoRepository.findAll());
		mvn.addObject("comboContas", dao.findAll());
		return mvn;
	}
	
	@RequestMapping("/pesquisa")
	private ModelAndView despesas() {
		ModelAndView mvn = new ModelAndView("despesas/lista");
		mvn.addObject("inquilino", new AInquilino());
		mvn.addObject("listDespesas", repository.findAll());
		return mvn;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	private ModelAndView getFindInquilino(AInquilino inquilino) {
		ModelAndView mvn = new ModelAndView("despesas/lista");
		mvn.addObject("inquilino", new AInquilino());
		inquilino = inquilinoRepository.findByNome(inquilino.getNome());
		mvn.addObject("listDespesas", inquilino.getListDespesas());
		return mvn;
	}
	@RequestMapping("/data/{status}/{id}")
	private ModelAndView getUpdateStatus(@PathVariable("status") Boolean status, @PathVariable("id") Long id) {
		Despesas despesas = repository.findById(id);
		despesas.setIsStatus(status);
		repository.save(despesas);
		return despesas();
	}

	@RequestMapping("/novo")
	private ModelAndView novo(@Valid Despesas despesa, BindingResult result) {
		if (result.hasErrors()) {
			return cadastro(despesa);
		}
		ModelAndView mvn = new ModelAndView("despesas/novo");
		despesa.setInquilino(inquilinoRepository.findById(despesa.getInquilino().getId()));
		despesa.setConta(dao.findById(despesa.getConta().getId()));
		repository.save(despesa);
		return mvn;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DecimalFormat df = new DecimalFormat();
	    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	    dfs.setGroupingSeparator('.');
	    dfs.setDecimalSeparator(',');
	    df.setDecimalFormatSymbols(dfs);
	    binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, df, true));
	}
}
