package dev.joaomarcelo.controleFinanceiro.dto;

import java.io.Serializable;
import java.util.Date;

import dev.joaomarcelo.controleFinanceiro.domain.Receita;

public class ReceitaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nomeReceita;

	private Double valorReceita;

	private Date dataReceita;

	private TipoReceitaDTO tipoReceitaDTO;

	public ReceitaDTO() {
	}

	public ReceitaDTO(Receita obj) {
		id = obj.getId();
		nomeReceita = obj.getNomeReceita();
		valorReceita = obj.getValorReceita();
		dataReceita = obj.getDataReceita();
		tipoReceitaDTO = new TipoReceitaDTO(obj.getTipoReceita().getId(), obj.getTipoReceita().getDescrição());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeReceita() {
		return nomeReceita;
	}

	public void setNomeReceita(String nomeReceita) {
		this.nomeReceita = nomeReceita;
	}

	public Double getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(Double valorReceita) {
		this.valorReceita = valorReceita;
	}

	public Date getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(Date dataReceita) {
		this.dataReceita = dataReceita;
	}

	public TipoReceitaDTO getTipoReceitaDTO() {
		return tipoReceitaDTO;
	}

	public void setTipoReceitaDTO(TipoReceitaDTO tipoReceitaDTO) {
		this.tipoReceitaDTO = tipoReceitaDTO;
	}

}
