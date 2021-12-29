package dev.joaomarcelo.controleFinanceiro.dto;

import dev.joaomarcelo.controleFinanceiro.domain.TipoReceita;

public class TipoReceitaDTO {

	private Integer id;

	private String descricao;

	public TipoReceitaDTO(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public TipoReceitaDTO(TipoReceita obj) {
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
