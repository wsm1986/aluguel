package com.aluguel.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

public class Despesas2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Conta Conta;

	@OneToOne
	private Inquilino inquilino;

	private BigDecimal valor;

	private Boolean isStatus;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtVenciomento;
	
	
	public Despesas2() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getConta() {
		return Conta;
	}

	public void setConta(Conta conta) {
		Conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(Boolean isStatus) {
		this.isStatus = isStatus;
	}

	public Calendar getDtVenciomento() {
		return dtVenciomento;
	}

	public void setDtVenciomento(Calendar dtVenciomento) {
		this.dtVenciomento = dtVenciomento;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}
	public String getDtConverter() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(dtVenciomento == null ? new Date() : dtVenciomento.getTime());
	}

	public void setDtConverter(String dtInicioConverter) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(dtInicioConverter));
			this.setDtVenciomento(calendar);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Long getIdInquilino() {
		return new Inquilino().getId();
	}

	public void setIdInquilino(Long idInquilino) {
		this.setInquilino(new Inquilino(idInquilino));
	}
	
	public Long getIdConta() {
		return new Conta().getId();
	}

	public void setIdConta(Long idConta) {
		this.setConta(new Conta(idConta));
	}
}