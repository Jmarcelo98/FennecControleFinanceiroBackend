package dev.joaomarcelo.controleFinanceiro.model.dto;

import java.util.List;

public class QuantidadeEDespesasMensais {

	private List<DespesaDTO> despesaDTO;
	private QuantidadeMensal qtd;

	public List<DespesaDTO> getDespesaDTO() {
		return despesaDTO;
	}

	public QuantidadeEDespesasMensais(List<DespesaDTO> despesaDTO, QuantidadeMensal qtd) {
		super();
		this.despesaDTO = despesaDTO;
		this.qtd = qtd;
	}

	public void setDespesaDTO(List<DespesaDTO> despesaDTO) {
		this.despesaDTO = despesaDTO;
	}

	public QuantidadeMensal getQtd() {
		return qtd;
	}

	public void setQtd(QuantidadeMensal qtd) {
		this.qtd = qtd;
	}

}
