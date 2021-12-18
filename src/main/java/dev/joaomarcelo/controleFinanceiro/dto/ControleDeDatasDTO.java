package dev.joaomarcelo.controleFinanceiro.dto;

import java.util.Date;

public class ControleDeDatasDTO {

	private Date dataMaisRecente;
	private Date dataMaisAntiga;

	public ControleDeDatasDTO(Date dataMaisRecente, Date dataMaisAntiga) {
		this.dataMaisRecente = dataMaisRecente;
		this.dataMaisAntiga = dataMaisAntiga;
	}

	public Date getDataMaisRecente() {
		return dataMaisRecente;
	}

	public void setDataMaisRecente(Date dataMaisRecente) {
		this.dataMaisRecente = dataMaisRecente;
	}

	public Date getDataMaisAntiga() {
		return dataMaisAntiga;
	}

	public void setDataMaisAntiga(Date dataMaisAntiga) {
		this.dataMaisAntiga = dataMaisAntiga;
	}

}
