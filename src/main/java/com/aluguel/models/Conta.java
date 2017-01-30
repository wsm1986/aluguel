package com.aluguel.models;

public class Conta {
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta() {
	}

	public Conta(String descricao) {
		super();
		this.descricao = descricao;
	}
}
