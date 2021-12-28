package dev.joaomarcelo.controleFinanceiro.dto;

import java.util.List;

public class QuantidadeEReceitaMensais {

	private List<ReceitaDTO> receitaDTO;
	private QuantidadeMensal qtd;

	public QuantidadeEReceitaMensais(List<ReceitaDTO> receitaDTO, QuantidadeMensal qtd) {
		super();
		this.receitaDTO = receitaDTO;
		this.qtd = qtd;
	}

	public List<ReceitaDTO> getReceitaDTO() {
		return receitaDTO;
	}

	public void setReceitaDTO(List<ReceitaDTO> receitaDTO) {
		this.receitaDTO = receitaDTO;
	}

	public QuantidadeMensal getQtd() {
		return qtd;
	}

	public void setQtd(QuantidadeMensal qtd) {
		this.qtd = qtd;
	}
}
