package com.aluguel.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Inquilino {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String numeroCasa;

	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtInicioContrato;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtFinalContrato;


	public int getId() {
		return id;
	}

	public void setId(int id) {
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


	public String getDtInicioConverter() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(dtInicioContrato.getTime());
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
		return sdf.format(dtFinalContrato.getTime());
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

}
