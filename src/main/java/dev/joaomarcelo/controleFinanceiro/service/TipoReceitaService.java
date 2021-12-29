package dev.joaomarcelo.controleFinanceiro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.TipoReceita;
import dev.joaomarcelo.controleFinanceiro.dto.TipoReceitaDTO;
import dev.joaomarcelo.controleFinanceiro.repository.TipoReceitaRepository;

@Service
public class TipoReceitaService {

	@Autowired
	private TipoReceitaRepository tipoReceitaRepository;

	public ResponseEntity<List<TipoReceitaDTO>> buscarTodosTiposReceitaPorIdUsuario(Integer id) {
		List<TipoReceita> tipoReceita = tipoReceitaRepository.findByUsuario(id);
		
		List<TipoReceitaDTO> listDto = tipoReceita.stream().map(obj -> new TipoReceitaDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok(listDto);
		
	}

}
