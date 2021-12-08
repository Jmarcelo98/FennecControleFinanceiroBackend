package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Receita;
import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.dto.ReceitaDTO;
import dev.joaomarcelo.controleFinanceiro.repository.ReceitaRepository;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;
import dev.joaomarcelo.controleFinanceiro.util.Datas;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class ReceitaService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ReceitaRepository receitaRepository;

	// BUSCAR TODAS AS RECEITAS DO USUARIO
	public List<Receita> buscarTodasAsReceitas(Integer id) {

		Optional<List<Receita>> listaReceitas = receitaRepository.findByIdUsuario(id);

		if (listaReceitas.get().size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_RECEITA);
		} else {
			return listaReceitas.get();
		}
	}

	public Integer quantidadeDeReceitas(Integer id, Integer mes, Integer ano) {

		return receitaRepository.quantidadeDeReceitas(ano, mes, id);

	}

	// BUSCAR TODAS AS RECEITAS DE EM UM MES/ANO DE ACORDO COM O ID DO USUARIO
	public List<Receita> buscarTodasAsReceitasMesAno(Integer id, Integer mes, Integer ano, Integer pagina,
			Integer linhasPorPagina) {

		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina);

		Optional<List<Receita>> listaReceitas = receitaRepository.findReceitaByIdUsuarioPeloMesEAno(ano, mes, id,
				pageRequest);

		if (listaReceitas.get().size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_RECEITA);
		} else {
			return listaReceitas.get();
		}
	}

	// ADICIONAR NOVA RECEITA
	public void adicionarReceita(ReceitaDTO receita, Integer idUsuario) {

		receita.setId(null);

		Usuario usuario = usuarioService.buscarPeloId(idUsuario);

		Receita novaReceita = new Receita(null, receita.getNomeReceita(), receita.getValorReceita(),
				receita.getDataReceita(), usuario);

		// novaReceita = (Receita) FormatarPalavras.caixaAltaClasse(novaReceita);

		receitaRepository.saveAll(Arrays.asList(novaReceita));
	}

	public void atualizarReceita(ReceitaDTO receita, Integer idUsuario) {
		Usuario usuario = usuarioService.buscarPeloId(idUsuario);

		Receita atualizarReceita = new Receita(receita.getId(), receita.getNomeReceita(), receita.getValorReceita(),
				receita.getDataReceita(), usuario);

		receitaRepository.save(atualizarReceita);

	}

	public ResponseEntity<?> valorReceitaMesAnoPesquisado(Integer ano, Integer mes, Integer id) {

		Double valorTotal = 0.0;

		List<Double> valorResgatado = receitaRepository.valoresReceitaDataAtual(ano, mes, id);

		if (valorResgatado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.SEM_RECEITA_MES_ATUAL);
		}

		for (int i = 0; i < valorResgatado.size(); i++) {
			valorTotal += valorResgatado.get(i);
		}

		return ResponseEntity.ok(valorTotal);
	}

	// DELETAR RECEITA PELO ID
	public void deletarReceitaPorId(Integer id) {
		receitaRepository.deleteById(id);
	}

	// VALOR DA RECEITA NA DATA ATUAL
	public ResponseEntity<?> valorReceitaDataAtual(Integer id) {

		Double valorTotal = 0.0;

		List<Double> valorResgatado = receitaRepository.valoresReceitaDataAtual(Datas.pegarAnoAtual(),
				Datas.pegarMesAtual(), id);

		if (valorResgatado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.SEM_RECEITA_MES_ATUAL);
		}

		for (int i = 0; i < valorResgatado.size(); i++) {
			valorTotal += valorResgatado.get(i);
		}

		return ResponseEntity.ok(valorTotal);
	}

	public List<String> anosEmQueExisteReceita(Integer id) {
		return receitaRepository.todosOsAnosQueExistemReceitas(id);
	}

}
