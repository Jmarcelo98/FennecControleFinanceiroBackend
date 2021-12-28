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
public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotBlank
	private String nomeReceita;

	@NotNull
	@NotBlank
	private Double valorReceita;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataReceita;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "tipo_receita_id")
	private TipoReceita tipoReceita;

	public Receita() {
	}

	public Receita(Integer id, String nomeReceita, Double valorReceita, Date dataReceita, Usuario usuario,
			TipoReceita tipoReceita) {
		super();
		this.id = id;
		this.nomeReceita = nomeReceita;
		this.valorReceita = valorReceita;
		this.dataReceita = dataReceita;
		this.usuario = usuario;
		this.tipoReceita = tipoReceita;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

}
