package com.aluguel.models;

import java.util.Calendar;

public class Inquilino {


	private String numeroCasa;
	
	private String nome;
	
	private Calendar  dtInicioContrato;
	
	private Calendar dtFinalContrato;

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

	public Inquilino() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inquilino(String numeroCasa, String nome, Calendar dtInicioContrato, Calendar dtFinalContrato) {
		super();
		this.numeroCasa = numeroCasa;
		this.nome = nome;
		this.dtInicioContrato = dtInicioContrato;
		this.dtFinalContrato = dtFinalContrato;
	}

	
}
