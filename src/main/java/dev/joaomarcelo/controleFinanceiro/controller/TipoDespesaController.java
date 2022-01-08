package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.model.dto.TipoDespesaDTO;
import dev.joaomarcelo.controleFinanceiro.model.dto.TipoDespesaEQuantidade;
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

	@GetMapping(path = "/paginacao")
	public ResponseEntity<TipoDespesaEQuantidade> buscarTodosTiposDespesaComPaginacao(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhasPorPagina", defaultValue = "5") Integer linhasPorPagina) {

		return ResponseEntity.ok().body(tipoDespesaService
				.buscarTodosTiposDespesaComPaginacao(idToken.pegarIdPeloToken(), pagina, linhasPorPagina));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deletarTipoDespesaPeloId(@PathVariable(value = "id") Integer id) {
		tipoDespesaService.deletarTipoDeDespesaPeloId(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<String> atualizarTipoDeDespesa(@RequestBody @Valid TipoDespesaDTO tipoDespesaDTO) {

		return tipoDespesaService.atualizarTipoDeDespesa(tipoDespesaDTO, idToken.pegarIdPeloToken());
//		receitaService.atualizarReceita(receita, idToken.pegarIdPeloToken());
	}

}
