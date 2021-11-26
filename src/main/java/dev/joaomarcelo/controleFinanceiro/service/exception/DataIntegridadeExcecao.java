package dev.joaomarcelo.controleFinanceiro.service.exception;

public class DataIntegridadeExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegridadeExcecao(String msg) {
		super(msg);
	}

	public DataIntegridadeExcecao(String msg, Throwable cause) {
		super(msg, cause);
	}

}
