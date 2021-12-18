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

import dev.joaomarcelo.controleFinanceiro.domain.Despesa;
import dev.joaomarcelo.controleFinanceiro.dto.DespesaDTO;
import dev.joaomarcelo.controleFinanceiro.dto.QuantidadeEDespesasMensais;
import dev.joaomarcelo.controleFinanceiro.dto.QuantidadeMensal;
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

//	@GetMapping(path = "/quantidade-mensal")
//	public ResponseEntity<Integer> quantidadeDeDespesasMensal(@RequestParam(value = "data") Date data) {
//		return ResponseEntity.ok(despesaService.quantidadeDeDespesasMensal(idToken.pegarIdPeloToken(), data));
//	}

	@GetMapping(path = "/dataMaisRecente")
	public ResponseEntity<Date> buscarDataMaisRecenteDaDespesa() {
		return ResponseEntity.ok(despesaService.buscarDataMaisRecenteDaDespesa(idToken.pegarIdPeloToken()));
	}

	@GetMapping(path = "data/mensal-anual")
	public ResponseEntity<QuantidadeEDespesasMensais> buscarTodasDespesasOuDeAcordoComOMesAno(
			@RequestParam(value = "data") Date data, @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhasPorPagina", defaultValue = "5") Integer linhasPorPagina) {

		List<Despesa> list = despesaService.buscarTodasAsDespesasMesAno(idToken.pegarIdPeloToken(), data, pagina,
				linhasPorPagina);

		List<DespesaDTO> listDto = list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());

		QuantidadeMensal qtd = new QuantidadeMensal(
				despesaService.quantidadeDeDespesasMensal(idToken.pegarIdPeloToken(), data));

		QuantidadeEDespesasMensais quantidadeEDespesasMensais = new QuantidadeEDespesasMensais(listDto, qtd);

		return ResponseEntity.ok().body(quantidadeEDespesasMensais);

	}

	@PostMapping
	public void adicionarDespesa(@RequestBody @Valid DespesaDTO despesa) {
		despesaService.adicionarDespesa(despesa, idToken.pegarIdPeloToken());
	}

	@PutMapping
	public void atualizarDespesa(@RequestBody @Valid DespesaDTO despesa) {
		despesaService.atualizarDespesa(despesa, idToken.pegarIdPeloToken());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarReceita(@PathVariable(value = "id") Integer id) {
		despesaService.deletarDespesaPorId(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "valor-total/mensal-anual")
	public ResponseEntity<?> valorDespesaMesAnoPesquisado(@RequestParam(value = "data") Date data) {
		ResponseEntity<?> resultadoDespesa = despesaService.valorDespesaMesAnoPesquisado(data,
				idToken.pegarIdPeloToken());
		return ResponseEntity.ok().body(resultadoDespesa).getBody();
	}

//	@GetMapping(path = "valorDespesaMes")
//	public ResponseEntity<?> valorDespesaDataAtual() {
//		ResponseEntity<?> resultadoDespesa = despesaService.valorDespesaDataAtual(idToken.pegarIdPeloToken());
//		return ResponseEntity.ok().body(resultadoDespesa).getBody();
//	}

}
