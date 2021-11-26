package dev.joaomarcelo.controleFinanceiro.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;
	private String senha;

	public UsuarioDTO() {

	}

	public UsuarioDTO(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
