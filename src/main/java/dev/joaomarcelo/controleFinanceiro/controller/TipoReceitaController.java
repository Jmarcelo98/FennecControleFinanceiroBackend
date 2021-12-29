package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.dto.TipoReceitaDTO;
import dev.joaomarcelo.controleFinanceiro.security.jwt.JwtUtils;
import dev.joaomarcelo.controleFinanceiro.service.TipoReceitaService;

@RestController
@RequestMapping("/tipoReceita")
@CrossOrigin("*")
public class TipoReceitaController {
	
	@Autowired
	private TipoReceitaService tipoReceitaService;

	@Autowired
	private JwtUtils idToken;
	
	@GetMapping
	public ResponseEntity<List<TipoReceitaDTO>> buscarTodosTiposReceitaPorIdUsuario() {
		return tipoReceitaService.buscarTodosTiposReceitaPorIdUsuario(idToken.pegarIdPeloToken());
	}

}
