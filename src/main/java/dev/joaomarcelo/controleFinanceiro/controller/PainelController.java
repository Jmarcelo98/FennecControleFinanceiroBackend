package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping
	public ResponseEntity<List<String>> buscarTodasReceitasOuDespesasAcordoComOMesAno() {

		List<String> anosDasReceitasEDespesas = painelService.anosASeremMostrados(idToken.pegarIdPeloToken());

		return ResponseEntity.ok().body(anosDasReceitasEDespesas);
	}

	@GetMapping(path = "receita/{ano}")
	public ResponseEntity<?> buscarReceitasMesDeAcordoComAno(@PathVariable(value = "ano") Integer ano) {
		return painelService.buscarReceitasMesDeAcordoComAno(idToken.pegarIdPeloToken(), ano);
	}

	@GetMapping(path = "despesa/{ano}")
	public ResponseEntity<?> buscarDespesasMesDeAcordoComAno(@PathVariable(value = "ano") Integer ano) {
		return painelService.buscarDespesasMesDeAcordoComAno(idToken.pegarIdPeloToken(), ano);
	}

	@GetMapping(path = "/valoresAnual/{ano}")
	public ResponseEntity<?> valoresAnualESaldo(@PathVariable(value = "ano") Integer ano) {
		return painelService.valoresAnualESaldo(idToken.pegarIdPeloToken(), ano);
	}

}
