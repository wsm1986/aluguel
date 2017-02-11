package com.aluguel.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Inquilino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Numero da Casa é obrigatório")
	private String numeroCasa;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtInicioContrato;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dtFinalContrato;

	@OneToMany(mappedBy = "inquilino", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<Despesas> listDespesas;

	@NotNull(message = "Valor do Contrato é obrigatório")
//	@Column(precision = 2, scale = 3)
	private BigDecimal valorContrato;

	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;

	@NotBlank(message = "Email é obrigatório")
	private String email;

	@NotBlank(message = "Tempo de Contrato é obrigatório")
	private String tempoContrato;

	@NotBlank(message = "Selecione o melhor dia para pagamento")
	private String diaVencimento;

	@NotBlank(message = "RG é Obrigatorio")
	private String rg;

	@NotBlank(message = "CPF é Obrigatorio")
	private String cpf;

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

	public Inquilino() {
	}

	public Inquilino(String numeroCasa, String nome, Calendar dtInicioContrato,
			Calendar dtFinalContrato) {
		this.numeroCasa = numeroCasa;
		this.nome = nome;
		this.dtInicioContrato = dtInicioContrato;
		this.dtFinalContrato = dtFinalContrato;
	}

	public String getDtInicioConverter() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(dtInicioContrato == null ? new Date()
				: dtInicioContrato.getTime());
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
		return sdf.format(dtFinalContrato == null ? new Date()
				: dtFinalContrato.getTime());
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

	public Inquilino(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTempoContrato() {
		return tempoContrato;
	}

	public void setTempoContrato(String tempoContrato) {
		this.tempoContrato = tempoContrato;
	}

	public String getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(String diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
