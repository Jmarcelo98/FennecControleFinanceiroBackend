package dev.joaomarcelo.controleFinanceiro.dto;

import dev.joaomarcelo.controleFinanceiro.domain.TipoDespesa;

public class TipoDespesaDTO {

	private Integer id;
	private String descricao;

	public TipoDespesaDTO(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public TipoDespesaDTO(TipoDespesa obj) {
		id = obj.getId();
		descricao = obj.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
