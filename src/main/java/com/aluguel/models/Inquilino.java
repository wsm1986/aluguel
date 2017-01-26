package com.aluguel.models;

public class Inquilino {
	private String numeroCasa;
	
	private String nome;
	
	private String dtInicioContrato;
	
	private String dtFinalContrato;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getDtInicioContrato() {
		return dtInicioContrato;
	}

	public void setDtInicioContrato(String dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	public String getDtFinalContrato() {
		return dtFinalContrato;
	}

	public void setDtFinalContrato(String dtFinalContrato) {
		this.dtFinalContrato = dtFinalContrato;
	}
}
