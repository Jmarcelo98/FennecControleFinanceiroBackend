package dev.joaomarcelo.controleFinanceiro.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.joaomarcelo.controleFinanceiro.service.exception.DataIntegridadeExcecao;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;

@ControllerAdvice
public class CapturarExcecaoController {

	@ExceptionHandler(ObjetoNaoEncontrado.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontrado e, HttpServletRequest req) {

		ErroPadrao err = new ErroPadrao(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}
	
	@ExceptionHandler(DataIntegridadeExcecao.class)
	public ResponseEntity<ErroPadrao> dataIntegridade(DataIntegridadeExcecao e, HttpServletRequest req) {

		ErroPadrao err = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

}
