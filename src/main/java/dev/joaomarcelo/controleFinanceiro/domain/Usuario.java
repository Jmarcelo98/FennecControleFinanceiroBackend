package dev.joaomarcelo.controleFinanceiro.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String nome;

	@NotNull
	private String sobrenome;

	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	@Min(6)
	private String senha;

	private String codigoRecuperacaoSenha;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<Despesa> despesa = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<Receita> receita = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<TipoReceita> tipoReceita = new ArrayList<>();

	public Usuario() {

	}

	public Usuario(String nome, String sobrenome, @Email String email, String senha, String codigoRecuperacaoSenha) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.codigoRecuperacaoSenha = codigoRecuperacaoSenha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Despesa> getDespesa() {
		return despesa;
	}

	public void setDespesa(List<Despesa> despesa) {
		this.despesa = despesa;
	}

	public List<Receita> getReceita() {
		return receita;
	}

	public void setReceita(List<Receita> receita) {
		this.receita = receita;
	}

	public String getCodigoRecuperacaoSenha() {
		return codigoRecuperacaoSenha;
	}

	public void setCodigoRecuperacaoSenha(String senhaProvisoria) {
		this.codigoRecuperacaoSenha = senhaProvisoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
