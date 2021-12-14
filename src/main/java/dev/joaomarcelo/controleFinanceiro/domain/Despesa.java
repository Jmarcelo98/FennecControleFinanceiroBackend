package dev.joaomarcelo.controleFinanceiro.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Despesa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotBlank
	private String nomeDespesa;

	@NotNull
	@NotBlank
	private Double valorDespesa;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataDespesa;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Despesa() {

	}

	public Despesa(Integer id, String nomeDespesa, Double valorDespesa, Date dataDespesa, Usuario usuario) {
		super();
		this.id = id;
		this.nomeDespesa = nomeDespesa;
		this.valorDespesa = valorDespesa;
		this.dataDespesa = dataDespesa;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDespesa() {
		return nomeDespesa;
	}

	public void setNomeDespesa(String nomeDespesa) {
		this.nomeDespesa = nomeDespesa;
	}

	public Double getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(Double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public Date getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
