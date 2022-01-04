package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.model.dto.TipoDespesaDTO;
import dev.joaomarcelo.controleFinanceiro.security.jwt.JwtUtils;
import dev.joaomarcelo.controleFinanceiro.service.TipoDespesaService;

@RestController
@RequestMapping("/tipo-despesa")
@CrossOrigin("*")
public class TipoDespesaController {

	@Autowired
	private TipoDespesaService tipoDespesaService;

	@Autowired
	private JwtUtils idToken;

	@GetMapping
	public ResponseEntity<List<TipoDespesaDTO>> buscarTodosTiposDespesaPorIdUsuario() {
		return tipoDespesaService.buscarTodosTiposDespesaPorIdUsuario(idToken.pegarIdPeloToken());
	}

}
