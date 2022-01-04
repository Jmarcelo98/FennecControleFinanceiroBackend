package dev.joaomarcelo.controleFinanceiro.model.dto;

public class PainelReceitaDTO {

	private Double valorTotalReceita;
	private Integer dataReceita;

	public PainelReceitaDTO(Double valorTotalReceita, Integer dataReceita) {
		super();
		this.valorTotalReceita = valorTotalReceita;
		this.dataReceita = dataReceita;

	}

	public Double getValorTotalReceita() {
		return valorTotalReceita;
	}

	public void setValorTotalReceita(Double valorTotalReceita) {
		this.valorTotalReceita = valorTotalReceita;
	}

	public Integer getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(Integer dataReceita) {
		this.dataReceita = dataReceita;
	}

}
