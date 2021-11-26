package dev.joaomarcelo.controleFinanceiro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.dto.PainelDespesaDTO;
import dev.joaomarcelo.controleFinanceiro.dto.PainelReceitaDTO;
import dev.joaomarcelo.controleFinanceiro.dto.PainelValoresAnuaisESaldo;
import dev.joaomarcelo.controleFinanceiro.repository.DespesaRepository;
import dev.joaomarcelo.controleFinanceiro.repository.ReceitaRepository;

@Service
public class PainelService {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private DespesaService despesaService;

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private DespesaRepository despesaRepository;

	public List<String> anosASeremMostrados(Integer id) {

		List<String> todosAnos = new ArrayList<>(receitaService.anosEmQueExisteReceita(id));

		List<String> despesasAnos = despesaService.anosEmQueExisteDespesa(id);

		todosAnos.addAll(despesasAnos);

		return todosAnos.stream().distinct().collect(Collectors.toList());

	}

	public ResponseEntity<?> buscarReceitasMesDeAcordoComAno(Integer id, Integer ano) {

		List<String> a = receitaRepository.buscarValoresEMesDeAcordoComAno(id, ano);

		List<PainelReceitaDTO> as = new ArrayList<>();

		for (int i = 0; i < a.size(); i++) {

			StringTokenizer st = new StringTokenizer(a.get(i));

			String data = st.nextToken(",");
			String valor = st.nextToken(",");

			PainelReceitaDTO painel = new PainelReceitaDTO(Double.valueOf(valor), Integer.valueOf(data));
			as.add(painel);
		}
		return ResponseEntity.ok(as);
	}

	public ResponseEntity<?> buscarDespesasMesDeAcordoComAno(Integer id, Integer ano) {

		List<String> a = despesaRepository.buscarValoresEMesDeAcordoComAno(id, ano);

		List<PainelDespesaDTO> as = new ArrayList<>();

		for (int i = 0; i < a.size(); i++) {

			StringTokenizer st = new StringTokenizer(a.get(i));

			String data = st.nextToken(",");
			String valor = st.nextToken(",");

			PainelDespesaDTO painel = new PainelDespesaDTO(Double.valueOf(valor), Integer.valueOf(data));
			as.add(painel);
		}
		return ResponseEntity.ok(as);
	}

	public ResponseEntity<?> valoresAnualESaldo(Integer id, Integer ano) {

		Double receita = receitaRepository.valorReceitaAnual(ano, id);
		Double despesa = despesaRepository.valorDespesaAnual(ano, id);

		if (receita == null) {
			receita = 0.0;
		}

		if (despesa == null) {
			despesa = 0.0;
		}

		PainelValoresAnuaisESaldo painel = new PainelValoresAnuaisESaldo(receita, despesa, receita - despesa);

		return ResponseEntity.ok(painel);
	}

}
