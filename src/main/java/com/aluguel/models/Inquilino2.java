package com.aluguel.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

public class Inquilino2 {

   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numeroCasa;

	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtInicioContrato;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtFinalContrato;

	@OneToMany(cascade = {CascadeType.ALL})
	private List<Despesas> listDespesas;
	
	private BigDecimal valorContrato;
	
	private String telefone;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
	
	
	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDtInicioContrato() {
		return dtInicioContrato;
	}

	public void setDtInicioContrato(Calendar dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	public Calendar getDtFinalContrato() {
		return dtFinalContrato;
	}

	public void setDtFinalContrato(Calendar dtFinalContrato) {
		this.dtFinalContrato = dtFinalContrato;
	}

	public Inquilino2() {
	}

	public Inquilino2(String numeroCasa, String nome, Calendar dtInicioContrato, Calendar dtFinalContrato) {
		this.numeroCasa = numeroCasa;
		this.nome = nome;
		this.dtInicioContrato = dtInicioContrato;
		this.dtFinalContrato = dtFinalContrato;
	}


	public String getDtInicioConverter() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(dtInicioContrato  == null ? new Date() : dtInicioContrato.getTime());
	}

	public void setDtInicioConverter(String dtInicioConverter) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(dtInicioConverter));
			this.setDtInicioContrato(calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getDtFinalConverter() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(dtFinalContrato == null ? new Date() : dtFinalContrato.getTime());
	}

	public void setDtFinalConverter(String dtFinalConverter) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(dtFinalConverter));
			this.setDtFinalContrato(calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Despesas> getListDespesas() {
		return listDespesas;
	}

	public void setListDespesas(List<Despesas> listDespesas) {
		this.listDespesas = listDespesas;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return nome;
	}

	public BigDecimal getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}

	public Inquilino2(Long id) {
		this.id = id;
	}

}