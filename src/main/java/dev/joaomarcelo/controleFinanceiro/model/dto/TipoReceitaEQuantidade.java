package dev.joaomarcelo.controleFinanceiro.model.dto;

import java.util.List;

public class TipoReceitaEQuantidade {

	private List<TipoReceitaDTO> tipoReceitaDTO;

	private QuantidadeItensCategoria quantidadeItensCategoria;

	public TipoReceitaEQuantidade(List<TipoReceitaDTO> tipoReceitaDTO,
			QuantidadeItensCategoria quantidadeItensCategoria) {
		super();
		this.tipoReceitaDTO = tipoReceitaDTO;
		this.quantidadeItensCategoria = quantidadeItensCategoria;
	}

	public List<TipoReceitaDTO> getTipoReceitaDTO() {
		return tipoReceitaDTO;
	}

	public void setTipoReceitaDTO(List<TipoReceitaDTO> tipoReceitaDTO) {
		this.tipoReceitaDTO = tipoReceitaDTO;
	}

	public QuantidadeItensCategoria getQuantidadeItensCategoria() {
		return quantidadeItensCategoria;
	}

	public void setQuantidadeItensCategoria(QuantidadeItensCategoria quantidadeItensCategoria) {
		this.quantidadeItensCategoria = quantidadeItensCategoria;
	}

}
