package dev.joaomarcelo.controleFinanceiro.service.exception;

public class ConstraintViolationExcecao extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ConstraintViolationExcecao(String msg) {
		super(msg);
	}

	public ConstraintViolationExcecao(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
