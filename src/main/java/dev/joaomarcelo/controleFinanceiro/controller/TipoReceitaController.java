package dev.joaomarcelo.controleFinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.model.dto.TipoReceitaDTO;
import dev.joaomarcelo.controleFinanceiro.model.dto.TipoReceitaEQuantidade;
import dev.joaomarcelo.controleFinanceiro.security.jwt.JwtUtils;
import dev.joaomarcelo.controleFinanceiro.service.TipoReceitaService;

@RestController
@RequestMapping("/tipo-receita")
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

	@GetMapping(path = "/paginacao")
	public ResponseEntity<TipoReceitaEQuantidade> buscarTodosTiposReceitaComPaginacao(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhasPorPagina", defaultValue = "5") Integer linhasPorPagina) {

		return ResponseEntity.ok().body(tipoReceitaService
				.buscarTodosTiposReceitaComPaginacao(idToken.pegarIdPeloToken(), pagina, linhasPorPagina));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deletarTipoReceitaPeloId(@PathVariable(value = "id") Integer id) {
		tipoReceitaService.deletarTipoDeReceitaPeloId(id);
		return ResponseEntity.noContent().build();
	}
}
