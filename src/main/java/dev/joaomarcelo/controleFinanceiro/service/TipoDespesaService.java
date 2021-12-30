package dev.joaomarcelo.controleFinanceiro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.TipoDespesa;
import dev.joaomarcelo.controleFinanceiro.dto.TipoDespesaDTO;
import dev.joaomarcelo.controleFinanceiro.repository.TipoDespesaRepository;

@Service
public class TipoDespesaService {

	@Autowired
	private TipoDespesaRepository tipoDespesaRepository;

	public ResponseEntity<List<TipoDespesaDTO>> buscarTodosTiposDespesaPorIdUsuario(Integer id) {
		List<TipoDespesa> tipoDepesa = tipoDespesaRepository.findByUsuario(id);

		List<TipoDespesaDTO> listDto = tipoDepesa.stream().map(obj -> new TipoDespesaDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok(listDto);

	}

}
