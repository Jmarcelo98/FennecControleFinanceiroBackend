package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.domain.Receita;
import dev.joaomarcelo.controleFinanceiro.dto.QuantidadeMensal;
import dev.joaomarcelo.controleFinanceiro.dto.ReceitaDTO;
import dev.joaomarcelo.controleFinanceiro.dto.QuantidadeEReceitaMensais;
import dev.joaomarcelo.controleFinanceiro.security.jwt.JwtUtils;
import dev.joaomarcelo.controleFinanceiro.service.ReceitaService;

@RestController
@RequestMapping("/receita")
@CrossOrigin(origins = "*")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private JwtUtils idToken;

//	@GetMapping(path = "/quantidade-mensal")
//	public ResponseEntity<Integer> quantidadeDeReceitasMensal(@RequestParam(value = "data") Date data) {
//		return ResponseEntity.ok(receitaService.quantidadeDeReceitasMensal(idToken.pegarIdPeloToken(), data));
//	}

	@GetMapping(path = "/dataMaisRecente")
	public ResponseEntity<Date> buscarDataMaisRecenteDaReceita() {
		return ResponseEntity.ok(receitaService.buscarDataMaisRecenteDaReceita(idToken.pegarIdPeloToken()));
	}

	@GetMapping(path = "data/mensal-anual")
	public ResponseEntity<QuantidadeEReceitaMensais> buscarTodasReceitasOuDeAcordoComOMesAno(
			@RequestParam(value = "data") Date data, @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhasPorPagina", defaultValue = "5") Integer linhasPorPagina) {

		List<Receita> list = receitaService.buscarTodasAsReceitasMesAno(idToken.pegarIdPeloToken(), data, pagina,
				linhasPorPagina);

		List<ReceitaDTO> listDto = list.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());

		QuantidadeMensal qtdReceita = new QuantidadeMensal(
				receitaService.quantidadeDeReceitasMensal(idToken.pegarIdPeloToken(), data));

		QuantidadeEReceitaMensais quantidadeEReceitaMensais = new QuantidadeEReceitaMensais(listDto, qtdReceita);

		return ResponseEntity.ok().body(quantidadeEReceitaMensais);
	}

	@PostMapping
	public void adicionarReceita(@RequestBody @Valid ReceitaDTO receita) {
		receitaService.adicionarReceita(receita, idToken.pegarIdPeloToken());
	}

	@PutMapping
	public void atualizarReceita(@RequestBody @Valid ReceitaDTO receita) {
		receitaService.atualizarReceita(receita, idToken.pegarIdPeloToken());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarReceita(@PathVariable(value = "id") Integer id) {
		receitaService.deletarReceitaPorId(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/valor-total/mensal-anual")
	public ResponseEntity<?> valorDaReceitaMesAnoPesquisado(@RequestParam(value = "data") Date data) {
		ResponseEntity<?> resultadoReceita = receitaService.valorReceitaMesAnoPesquisado(data,
				idToken.pegarIdPeloToken());
		return ResponseEntity.ok().body(resultadoReceita).getBody();
	}

//	@GetMapping(path = "valorReceitaMes")
//	public ResponseEntity<?> valorReceitaDataAtual() {
//		ResponseEntity<?> resultadoReceita = receitaService.valorReceitaDataAtual(idToken.pegarIdPeloToken());
//		return ResponseEntity.ok().body(resultadoReceita).getBody();
//	}

}
