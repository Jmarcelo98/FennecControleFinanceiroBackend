package dev.joaomarcelo.controleFinanceiro.payload.response;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Integer id;
	private String nome;
	private String sobrenome;
	private Boolean senhaProvisoria;

//	public JwtResponse(String token, Integer id, String usuario) {
//		super();
//		this.token = token;
//		this.id = id;
//		this.usuario = usuario;
//	}

	public JwtResponse(String token, Integer id, String nome, String sobrenome, Boolean senhaProvisoria) {
		super();
		this.token = token;
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.senhaProvisoria = senhaProvisoria;
	}

//	public JwtResponse(String token, Boolean senhaProvisoria) {
//		super();
//		this.senhaProvisoria = senhaProvisoria;
//		this.token = token;
//	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getSenhaProvisoria() {
		return senhaProvisoria;
	}

	public void setSenhaProvisoria(Boolean senhaProvisoria) {
		this.senhaProvisoria = senhaProvisoria;
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

}
