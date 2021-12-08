package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.ArrayList;
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
import dev.joaomarcelo.controleFinanceiro.dto.ReceitaDTO;
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

	@GetMapping(path = "/quantidade/{ano}/{mes}")
	public ResponseEntity<Integer> quantidadeDeReceitas(@PathVariable(value = "ano") Integer ano,
			@PathVariable(value = "mes") Integer mes) {
		return ResponseEntity.ok(receitaService.quantidadeDeReceitas(idToken.pegarIdPeloToken(), mes, ano));
	}

	// PEGAR TODAS AS RECEITA DO USUARIO OU DE ALGUM MÊS/ANO ESPECIFICO
	@GetMapping(path = "/{ano}/{mes}")
	public ResponseEntity<List<ReceitaDTO>> buscarTodasReceitasOuDeAcordoComOMesAno(
			@PathVariable(value = "ano") Integer ano, @PathVariable(value = "mes") Integer mes,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhasPorPagina", defaultValue = "5") Integer linhasPorPagina) {

		List<Receita> list = new ArrayList<>();

		if (ano != null && mes != null) {
			list = receitaService.buscarTodasAsReceitasMesAno(idToken.pegarIdPeloToken(), mes, ano, pagina,
					linhasPorPagina);
		} else {
			list = receitaService.buscarTodasAsReceitas(idToken.pegarIdPeloToken());
		}
		List<ReceitaDTO> listDto = list.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// ADICIONAR NOVA RECEITA
	@PostMapping
	public void adicionarReceita(@RequestBody @Valid ReceitaDTO receita, String data) {
		receitaService.adicionarReceita(receita, idToken.pegarIdPeloToken());
	}

	@PutMapping
	public void atualizarReceita(@RequestBody @Valid ReceitaDTO receita) {
		receitaService.atualizarReceita(receita, idToken.pegarIdPeloToken());
	}

	// DELETAR RECEITA PELO ID
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarReceita(@PathVariable(value = "id") Integer id) {
		receitaService.deletarReceitaPorId(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "valorReceitaMes/{ano}/{mes}")
	public ResponseEntity<?> valorReceitaMesAnoPesquisado(@PathVariable(value = "ano") Integer ano,
			@PathVariable(value = "mes") Integer mes) {
		ResponseEntity<?> resultadoReceita = receitaService.valorReceitaMesAnoPesquisado(ano, mes,
				idToken.pegarIdPeloToken());
		return ResponseEntity.ok().body(resultadoReceita).getBody();
	}

	@GetMapping(path = "valorReceitaMes")
	public ResponseEntity<?> valorReceitaDataAtual() {
		ResponseEntity<?> resultadoReceita = receitaService.valorReceitaDataAtual(idToken.pegarIdPeloToken());
		return ResponseEntity.ok().body(resultadoReceita).getBody();
	}

}
