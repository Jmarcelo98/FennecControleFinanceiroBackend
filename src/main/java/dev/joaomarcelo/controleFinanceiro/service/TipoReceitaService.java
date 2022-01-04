package dev.joaomarcelo.controleFinanceiro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.model.domain.TipoReceita;
import dev.joaomarcelo.controleFinanceiro.model.dto.QuantidadeItensCategoria;
import dev.joaomarcelo.controleFinanceiro.model.dto.TipoReceitaDTO;
import dev.joaomarcelo.controleFinanceiro.model.dto.TipoReceitaEQuantidade;
import dev.joaomarcelo.controleFinanceiro.repository.TipoReceitaRepository;
import dev.joaomarcelo.controleFinanceiro.service.exception.DataIntegridadeExcecao;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class TipoReceitaService {

	@Autowired
	private TipoReceitaRepository tipoReceitaRepository;

	public ResponseEntity<List<TipoReceitaDTO>> buscarTodosTiposReceitaPorIdUsuario(Integer id) {
		List<TipoReceita> tipoReceita = tipoReceitaRepository.findByUsuario(id);

		List<TipoReceitaDTO> listDto = tipoReceita.stream().map(obj -> new TipoReceitaDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok(listDto);

	}

	public TipoReceitaEQuantidade buscarTodosTiposReceitaComPaginacao(Integer id, Integer pagina,
			Integer linhasPorPagina) {

		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina);

		List<TipoReceita> list = tipoReceitaRepository.findTipoReceitaPaginacao(id, pageRequest);

		List<TipoReceitaDTO> listDto = list.stream().map(obj -> new TipoReceitaDTO(obj)).collect(Collectors.toList());

		TipoReceitaEQuantidade tcq = new TipoReceitaEQuantidade(listDto,
				new QuantidadeItensCategoria(tipoReceitaRepository.contador(id)));

		if (list.size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_TIPO_RECEITA_CADASTRADA);
		} else {
			return tcq;
		}

	}

	public void deletarTipoDeReceitaPeloId(Integer id) {

		try {
			tipoReceitaRepository.deleteById(id);
//			return MensagensPersonalizadas.TIPO_RECEITA_DELETADA;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegridadeExcecao(MensagensPersonalizadas.IMPOSSIVEL_DELETAR_TIPO_RECEITA);
		}

	}
}
