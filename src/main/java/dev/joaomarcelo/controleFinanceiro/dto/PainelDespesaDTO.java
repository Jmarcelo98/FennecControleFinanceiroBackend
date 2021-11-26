package dev.joaomarcelo.controleFinanceiro.dto;

public class PainelDespesaDTO {

	private Double valorTotalDespesa;
	private Integer dataDespesa;

	public PainelDespesaDTO(Double valorTotalDespesa, Integer dataDespesa) {
		super();
		this.valorTotalDespesa = valorTotalDespesa;
		this.dataDespesa = dataDespesa;

	}

	public Double getValorTotalDespesa() {
		return valorTotalDespesa;
	}

	public void setValorTotalDespesa(Double valorTotalDespesa) {
		this.valorTotalDespesa = valorTotalDespesa;
	}

	public Integer getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Integer dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

}
