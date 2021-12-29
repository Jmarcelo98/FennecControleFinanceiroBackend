package dev.joaomarcelo.controleFinanceiro.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TipoReceita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotBlank
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@JsonIgnore
	@OneToMany(mappedBy = "tipoReceita", cascade = CascadeType.ALL)
	private List<Receita> receita = new ArrayList<>();
//	private Receita receita;

	public TipoReceita(Integer id, String descricao, Usuario usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.usuario = usuario;
	}

	public TipoReceita() {

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

	public void setDescrição(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Receita> getReceita() {
		return receita;
	}

	public void setReceita(List<Receita> receita) {
		this.receita = receita;
	}

}
