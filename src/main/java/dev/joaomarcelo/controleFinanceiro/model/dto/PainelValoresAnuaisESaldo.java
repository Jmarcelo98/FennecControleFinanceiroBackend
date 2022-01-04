package dev.joaomarcelo.controleFinanceiro.model.dto;

public class PainelValoresAnuaisESaldo {

	private Double valorAnualReceita;
	private Double valorAnualDespesa;
	private Double saldoFinalAnual;

	public PainelValoresAnuaisESaldo(Double valorAnualReceita, Double valorAnualDespesa, Double saldoFinalAnual) {
		super();
		this.valorAnualReceita = valorAnualReceita;
		this.valorAnualDespesa = valorAnualDespesa;
		this.saldoFinalAnual = saldoFinalAnual;
	}

	public Double getValorAnualReceita() {
		return valorAnualReceita;
	}

	public void setValorAnualReceita(Double valorAnualReceita) {
		this.valorAnualReceita = valorAnualReceita;
	}

	public Double getValorAnualDespesa() {
		return valorAnualDespesa;
	}

	public void setValorAnualDespesa(Double valorAnualDespesa) {
		this.valorAnualDespesa = valorAnualDespesa;
	}

	public Double getSaldoFinalAnual() {
		return saldoFinalAnual;
	}

	public void setSaldoFinalAnual(Double saldoFinalAnual) {
		this.saldoFinalAnual = saldoFinalAnual;
	}

}
