package com.aluguel.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.aluguel.models.Conta;
import com.aluguel.models.Despesas;
import com.aluguel.models.Inquilino;
import com.aluguel.models.MessageWeb;
import com.aluguel.repository.ContaRepository;
import com.aluguel.repository.InquilinoRepository;
import com.aluguel.services.GerarPdf;

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

	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private GerarPdf gerarpdf;

	private final String aluguel = "Aluguel";

	@RequestMapping("/")
	private ModelAndView home() {
		ModelAndView mvn = new ModelAndView("index");
		return mvn;
	}

	@RequestMapping("/form")
	private ModelAndView cadastro(Inquilino inquilino) {
		ModelAndView mvn = new ModelAndView("inquilino/novo");
		return mvn;
	}

	@RequestMapping("/novo")
	private ModelAndView novo(@Valid Inquilino inquilino, BindingResult result) {
		if (result.hasErrors()) {
			return cadastro(inquilino);
		}
		List<Despesas> lista = new ArrayList<Despesas>();
		Despesas despesas;
		Conta conta = contaRepository.findByDescricao(aluguel);

		Calendar dtAux = Calendar.getInstance();
		dtAux.add(Calendar.DAY_OF_MONTH, Integer.valueOf(inquilino.getDiaVencimento()));

		inquilino.setDtInicioContrato(Calendar.getInstance());

		Calendar finalContrato = Calendar.getInstance();
		finalContrato.add(Calendar.YEAR, Integer.valueOf(inquilino.getTempoContrato()));
		inquilino.setDtFinalContrato(finalContrato);

		while (!dtAux.getTime().after(inquilino.getDtFinalContrato().getTime())) {
			Calendar calendar = new GregorianCalendar();
			calendar.set(dtAux.get(Calendar.YEAR), dtAux.get(Calendar.MONTH), 0);
			calendar.set(GregorianCalendar.DAY_OF_MONTH, Integer.valueOf(inquilino.getDiaVencimento()));
			despesas = new Despesas();
			despesas.setInquilino(inquilino);
			despesas.setConta(conta);
			despesas.setDtVenciomento(calendar);
			despesas.setIsStatus(Boolean.FALSE);
			despesas.setValor(inquilino.getValorContrato());
			lista.add(despesas);
			dtAux.add(Calendar.MONTH, 1);

		}

		inquilino.setListDespesas(lista);
		inquilinoRepository.save(inquilino);
		ModelAndView mvn = new ModelAndView("inquilino/novo");
		inquilino = new Inquilino();
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

	@RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET)
	public ModelAndView imprimirPdf(@PathVariable("id") Long id) {
		Inquilino inquilino = inquilinoRepository.findById(id);
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:contratoAluguel.jrxml");
		view.setApplicationContext(appContext);
		return new ModelAndView(view,gerarpdf.contratoAluguel(inquilino));
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
