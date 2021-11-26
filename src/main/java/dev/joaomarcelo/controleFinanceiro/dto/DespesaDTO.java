package dev.joaomarcelo.controleFinanceiro.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dev.joaomarcelo.controleFinanceiro.domain.Despesa;

public class DespesaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nomeDespesa;

	private Double valorDespesa;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDespesa;

	public DespesaDTO() {
	}

	public DespesaDTO(Despesa despesa) {
		id = despesa.getId();
		nomeDespesa = despesa.getNomeDespesa();
		valorDespesa = despesa.getValorDespesa();
		dataDespesa = despesa.getDataDespesa();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDespesa() {
		return nomeDespesa;
	}

	public void setNomeDespesa(String nomeDespesa) {
		this.nomeDespesa = nomeDespesa;
	}

	public Double getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(Double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public Date getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

}
