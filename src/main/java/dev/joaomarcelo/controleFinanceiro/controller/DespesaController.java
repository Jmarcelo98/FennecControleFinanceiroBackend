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
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.domain.Despesa;
import dev.joaomarcelo.controleFinanceiro.dto.DespesaDTO;
import dev.joaomarcelo.controleFinanceiro.security.jwt.JwtUtils;
import dev.joaomarcelo.controleFinanceiro.service.DespesaService;

@RestController
@RequestMapping("/despesa")
@CrossOrigin(origins = "*")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;

	@Autowired
	private JwtUtils idToken;

	// PEGAR TODAS AS DESPESAS DO USUARIO OU DE ALGUM MÊS/ANO ESPECIFICO
	@GetMapping(path = "/{ano}/{mes}")
	public ResponseEntity<List<DespesaDTO>> buscarTodasDespesasOuDeAcordoComOMesAno(
			@PathVariable(value = "ano") Integer ano, @PathVariable(value = "mes") Integer mes) {

		List<Despesa> list = new ArrayList<>();

		if (ano != null && mes != null) {
			list = despesaService.buscarTodasAsDespesasMesAno(idToken.pegarIdPeloToken(), mes, ano);
		} else {
			list = despesaService.buscarTodasAsDespesas(idToken.pegarIdPeloToken());
		}
		List<DespesaDTO> listDto = list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// ADICIONAR NOVA DESPESA
	@PostMapping
	public void adicionarDespesa(@RequestBody @Valid DespesaDTO despesa) {
		despesaService.adicionarDespesa(despesa, idToken.pegarIdPeloToken());
	}

	@PutMapping
	public void atualizarDespesa(@RequestBody @Valid DespesaDTO despesa) {
		despesaService.atualizarDespesa(despesa, idToken.pegarIdPeloToken());
	}

	// DELETAR DESPESA PELO ID
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarReceita(@PathVariable(value = "id") Integer id) {
		despesaService.deletarDespesaPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "valorDespesaMes/{ano}/{mes}")
	public ResponseEntity<?> valorDespesaMesAnoPesquisado(@PathVariable(value = "ano") Integer ano,
			@PathVariable(value = "mes") Integer mes) {
		ResponseEntity<?> resultadoDespesa = despesaService.valorDespesaMesAnoPesquisado(ano, mes, idToken.pegarIdPeloToken());
		return ResponseEntity.ok().body(resultadoDespesa).getBody();
	}

	@GetMapping(path = "valorDespesaMes")
	public ResponseEntity<?> valorDespesaDataAtual() {
		ResponseEntity<?> resultadoDespesa = despesaService.valorDespesaDataAtual(idToken.pegarIdPeloToken());
		return ResponseEntity.ok().body(resultadoDespesa).getBody();
	}

}
