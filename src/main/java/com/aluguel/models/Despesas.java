package com.aluguel.models;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Despesas {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	private Inquilino inquilino;
	
	@OneToOne
	private Conta Conta;

	private BigDecimal valor;
	
	private Boolean isStatus;
	
	private Calendar dtVenciomento;

	
	public Despesas() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
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


}
