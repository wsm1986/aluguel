package com.aluguel.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aluguel.models.Despesas;
import com.aluguel.models.Inquilino;

import domain.Locador;

@Service
public class GerarPdf {

	public Map<String, Object> contratoAluguel(Inquilino inquilino) {
		Locador locador = new AdministratorService().getLocador();
		Map<String, Object> params = new HashMap<>();
		params.put("nome_inquilino", String.valueOf(inquilino.getNome()));
		params.put("rg_inquilino", String.valueOf(inquilino.getRg()));
		params.put("cpf_inquilino", String.valueOf(inquilino.getCpf()));
		params.put("valor_contrato", String.valueOf(inquilino.getValorContrato()));
		params.put("nome_locador", String.valueOf(locador.getNome()));
		params.put("rg_locador", String.valueOf(locador.getRg()));
		params.put("cpf_locador", String.valueOf(locador.getCpf()));
		params.put("desc_valor_contrato", "");
		params.put("dt_inicio_contrato", inquilino.getDtInicioConverter());
		params.put("dt_final_contrato", inquilino.getDtFinalConverter());
		params.put("valor_deposito", String.valueOf(inquilino.getValorContrato().multiply(new BigDecimal(2))));
		params.put("desc_valor_deposito", "");
		params.put("dia_vencimento", String.valueOf(inquilino.getDiaVencimento()));
		params.put("tempo_contrato", String.valueOf(inquilino.getTempoContrato()));
		return params;
	}

	public Map<String, Object> comprovantePagamento(Despesas despesas) {
		Map<String, Object> params = new HashMap<>();
		params.put("nome_inquilino", String.valueOf(despesas.getInquilino().getNome()));
		params.put("endereco_propriedade", String.valueOf("Rua Arara n- 433, Vila Ayrosa - Osasco/SP"));
		params.put("cpf_inquilino", String.valueOf(despesas.getInquilino().getCpf()));
		params.put("valor_pago", String.valueOf(despesas.getValor()));
		params.put("cidade", String.valueOf("Osasco"));
		params.put("data_pagamento", new Date());
		return params;
	}
}
