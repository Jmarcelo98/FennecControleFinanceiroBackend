package dev.joaomarcelo.controleFinanceiro.model.dto;

import java.util.List;

public class TipoDespesaEQuantidade {

	private List<TipoDespesaDTO> tipoDespesaDTO;

	private QuantidadeItensCategoria quantidadeItensCategoria;

	public TipoDespesaEQuantidade(List<TipoDespesaDTO> tipoDespesaDTO,
			QuantidadeItensCategoria quantidadeItensCategoria) {
		super();
		this.tipoDespesaDTO = tipoDespesaDTO;
		this.quantidadeItensCategoria = quantidadeItensCategoria;
	}

	public List<TipoDespesaDTO> getTipoDespesaDTO() {
		return tipoDespesaDTO;
	}

	public void setTipoDespesaDTO(List<TipoDespesaDTO> tipoDespesaDTO) {
		this.tipoDespesaDTO = tipoDespesaDTO;
	}

	public QuantidadeItensCategoria getQuantidadeItensCategoria() {
		return quantidadeItensCategoria;
	}

	public void setQuantidadeItensCategoria(QuantidadeItensCategoria quantidadeItensCategoria) {
		this.quantidadeItensCategoria = quantidadeItensCategoria;
	}

}
