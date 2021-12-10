package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.security.jwt.JwtUtils;
import dev.joaomarcelo.controleFinanceiro.service.PainelService;

@RestController
@RequestMapping("/painel")
@CrossOrigin("*")
public class PainelController {

	@Autowired
	private PainelService painelService;

	@Autowired
	private JwtUtils idToken;

	// PEGAR TODOS OS ANOS
	@GetMapping(path = "/anos")
	public ResponseEntity<List<String>> buscarTodosOsAnosEmQuePossuiReceitaOuDespesa() {

		List<String> anosDasReceitasEDespesas = painelService.buscarTodosOsAnosEmQuePossuiReceitaOuDespesa(idToken.pegarIdPeloToken());

		return ResponseEntity.ok().body(anosDasReceitasEDespesas);
	}
	
	@GetMapping(path = "/ano")
	public ResponseEntity<?> painelValores(@RequestParam Integer ano) {
		return painelService.painelValores(idToken.pegarIdPeloToken(), ano);
	}

}
