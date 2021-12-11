package dev.joaomarcelo.controleFinanceiro.dto;

public class MesAnoDTO {

	private Integer mes;
	private Integer ano;

	public MesAnoDTO(Integer mes, Integer ano) {
		super();
		this.mes = mes;
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
