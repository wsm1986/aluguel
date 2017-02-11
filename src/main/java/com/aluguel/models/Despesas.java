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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Despesas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@NotNull(message = "Favor Cadastrar uma Conta antes de Lançar uma Despesa")
	private Conta Conta;

	@OneToOne
	@NotNull(message = "Favor Cadastrar um Inquilino antes de Lançar uma Despesa")
	private AInquilino inquilino;

	@NotNull(message = "valor é obrigatório")
	private BigDecimal valor;

	private Boolean isStatus;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Data Vencimento é obrigatório")
	private Calendar dtVenciomento;
	
	
	public Despesas() {
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

	public AInquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(AInquilino inquilino) {
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
		return new AInquilino().getId();
	}

	public void setIdInquilino(Long idInquilino) {
		this.setInquilino(new AInquilino(idInquilino));
	}
	
	public Long getIdConta() {
		return new Conta().getId();
	}

	public void setIdConta(Long idConta) {
		this.setConta(new Conta(idConta));
	}
}
