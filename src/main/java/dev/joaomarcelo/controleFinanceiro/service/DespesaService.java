package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Despesa;
import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.dto.DespesaDTO;
import dev.joaomarcelo.controleFinanceiro.repository.DespesaRepository;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;
import dev.joaomarcelo.controleFinanceiro.util.Datas;
import dev.joaomarcelo.controleFinanceiro.util.FormatarPalavras;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class DespesaService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DespesaRepository despesaRepository;

	// private final String semDespesasMesAtual =
	// MensagensPersonalizadas.SEM_DESPESA_MES_ATUAL;

	public ResponseEntity<?> valorDespesaMesAnoPesquisado(Integer ano, Integer mes, Integer id) {

		Double valorTotal = 0.0;

		List<Double> valorResgatado = despesaRepository.valoresDespesaDataAtual(ano, mes, id);

		if (valorResgatado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.SEM_DESPESA_MES_ATUAL);
		}

		for (int i = 0; i < valorResgatado.size(); i++) {
			valorTotal += valorResgatado.get(i);
		}

		return ResponseEntity.ok(valorTotal);
	}

	// BUSCAR TODAS AS DESPESA DO USUARIO
	public List<Despesa> buscarTodasAsDespesas(Integer id) {

		Optional<List<Despesa>> listaDespesas = despesaRepository.findByIdUsuario(id);

		if (listaDespesas.get().size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_DESPESA);
		} else {
			return listaDespesas.get();
		}
	}

	// BUSCAR TODAS AS DESPESAS DE EM UM MES/ANO DE ACORDO COM O ID DO USUARIO
	public List<Despesa> buscarTodasAsDespesasMesAno(Integer id, Integer mes, Integer ano) {

		Optional<List<Despesa>> listaDespesa = despesaRepository.findByIdUsuarioMes(ano, mes, id);

		if (listaDespesa.get().size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_DESPESA);
		} else {
			return listaDespesa.get();
		}
	}

	// ADICIONAR NOVA DESPESA
	public Despesa adicionarDespesa(DespesaDTO despesa, Integer id) {
		despesa.setId(null);
		Usuario usuario = usuarioService.buscarPeloId(id);

		Despesa novaDespesa = new Despesa(null, despesa.getNomeDespesa(), despesa.getValorDespesa(),
				despesa.getDataDespesa(), usuario);

		novaDespesa = (Despesa) FormatarPalavras.caixaAltaClasse(novaDespesa);

		despesaRepository.saveAll(Arrays.asList(novaDespesa));
		return null;
	}

	public void atualizarDespesa(DespesaDTO despesa, Integer idUsuario) {
		Usuario usuario = usuarioService.buscarPeloId(idUsuario);

		Despesa atualizarDespesa = new Despesa(despesa.getId(), despesa.getNomeDespesa(), despesa.getValorDespesa(),
				despesa.getDataDespesa(), usuario);

		despesaRepository.save(atualizarDespesa);

	}

	// DELETAR DESPESA PELO ID
	public void deletarDespesaPorId(Integer id) {
		despesaRepository.deleteById(id);
	}

	// VALOR DA RECEITA NA DATA ATUAL
	public ResponseEntity<?> valorDespesaDataAtual(Integer id) {

		Double valorTotal = 0.0;

//		Optional<List<Despesa>> listaDespesas = despesaRepository.findByIdUsuario(id);
//
//		if (listaDespesas.get().size() == 0) {
//			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_DESPESA_MES_ATUAL);
//		}

		List<Double> valorResgatado = despesaRepository.valoresDespesaDataAtual(Datas.pegarAnoAtual(),
				Datas.pegarMesAtual(), id);

		if (valorResgatado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.SEM_DESPESA_MES_ATUAL);
//			return ResponseEntity.ok(semDespesasMesAtual);
//			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_DESPESA_MES_ATUAL);
		}

		for (int i = 0; i < valorResgatado.size(); i++) {
			valorTotal += valorResgatado.get(i);
		}

		return ResponseEntity.ok(valorTotal);
	}

	public List<String> anosEmQueExisteDespesa(Integer id) {
		return despesaRepository.todosOsAnosQueExistemDespesas(id);
	}

}
