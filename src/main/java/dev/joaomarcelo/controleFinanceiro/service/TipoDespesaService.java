package dev.joaomarcelo.controleFinanceiro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.model.domain.TipoDespesa;
import dev.joaomarcelo.controleFinanceiro.model.dto.QuantidadeItensCategoria;
import dev.joaomarcelo.controleFinanceiro.model.dto.TipoDespesaDTO;
import dev.joaomarcelo.controleFinanceiro.model.dto.TipoDespesaEQuantidade;
import dev.joaomarcelo.controleFinanceiro.repository.TipoDespesaRepository;
import dev.joaomarcelo.controleFinanceiro.service.exception.DataIntegridadeExcecao;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class TipoDespesaService {

	@Autowired
	private TipoDespesaRepository tipoDespesaRepository;

	@Autowired
	private UsuarioService usuarioService;

	public ResponseEntity<List<TipoDespesaDTO>> buscarTodosTiposDespesaPorIdUsuario(Integer id) {
		List<TipoDespesa> tipoDepesa = tipoDespesaRepository.findByUsuario(id);

		List<TipoDespesaDTO> listDto = tipoDepesa.stream().map(obj -> new TipoDespesaDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok(listDto);

	}

	public TipoDespesaEQuantidade buscarTodosTiposDespesaComPaginacao(Integer id, Integer pagina,
			Integer linhasPorPagina) {

		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina);

		List<TipoDespesa> list = tipoDespesaRepository.findTipoDespesaPaginacao(id, pageRequest);

		List<TipoDespesaDTO> listDto = list.stream().map(obj -> new TipoDespesaDTO(obj)).collect(Collectors.toList());

		TipoDespesaEQuantidade tcq = new TipoDespesaEQuantidade(listDto,
				new QuantidadeItensCategoria(tipoDespesaRepository.contador(id)));

		if (list.size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_TIPO_RECEITA_CADASTRADA);
		} else {
			return tcq;
		}

	}

	public void deletarTipoDeDespesaPeloId(Integer id) {

		try {
			tipoDespesaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegridadeExcecao(MensagensPersonalizadas.IMPOSSIVEL_DELETAR_TIPO_RECEITA);
		}

	}

	public ResponseEntity<String> atualizarTipoDeDespesa(TipoDespesaDTO tipoDespesaDTO, Integer id) {

		if (tipoDespesaRepository.existsByDescricaoAndUsuarioId(tipoDespesaDTO.getDescricao(), id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(MensagensPersonalizadas.IMPOSSIVEL_ATUALIZAR_TIPO_DESPESA);
		} else {
			tipoDespesaRepository.save(new TipoDespesa(tipoDespesaDTO.getId(), tipoDespesaDTO.getDescricao(),
					usuarioService.buscarPeloId(id)));
			return ResponseEntity.noContent().build();
		}

	}

}
