package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Despesa;
import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.dto.DespesaDTO;
import dev.joaomarcelo.controleFinanceiro.dto.MesAnoDTO;
import dev.joaomarcelo.controleFinanceiro.repository.DespesaRepository;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;
import dev.joaomarcelo.controleFinanceiro.util.Datas;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class DespesaService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DespesaRepository despesaRepository;

	private Datas datas = new Datas();

	public Integer quantidadeDeDespesasMensal(Integer id, Date data) {

		MesAnoDTO mesAnoDTO = datas.retornarAnoEMes(data);

		return despesaRepository.quantidadeDeDespesas(mesAnoDTO.getAno(), mesAnoDTO.getMes(), id);

	}

	public Date buscarDataMaisRecenteDaDespesa(Integer id) {

		Date dataDespesaMaisRecente = despesaRepository.buscarDataMaisRecenteDaDespesa(id);

		if (dataDespesaMaisRecente == null) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_DESPESA_CADASTRADO);
		}

		return dataDespesaMaisRecente;

	}

	public List<Despesa> buscarTodasAsDespesasMesAno(Integer id, Date data, Integer pagina, Integer linhasPorPagina) {

		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina);

		MesAnoDTO mesAnoDTO = datas.retornarAnoEMes(data);

		List<Despesa> listaDespesa = despesaRepository.findDespesaByIdUsuarioPeloMesEAno(mesAnoDTO.getAno(), mesAnoDTO.getMes(), id,
				pageRequest);

		if (listaDespesa.size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_DESPESA);
		} else {
			return listaDespesa;
		}
	}

	public void adicionarDespesa(DespesaDTO despesa, Integer id) {
		despesa.setId(null);
		Usuario usuario = usuarioService.buscarPeloId(id);

		Despesa novaDespesa = new Despesa(null, despesa.getNomeDespesa(), despesa.getValorDespesa(),
				despesa.getDataDespesa(), usuario);

		despesaRepository.saveAll(Arrays.asList(novaDespesa));
	}
	
	public void atualizarDespesa(DespesaDTO despesa, Integer idUsuario) {
		Usuario usuario = usuarioService.buscarPeloId(idUsuario);

		Despesa atualizarDespesa = new Despesa(despesa.getId(), despesa.getNomeDespesa(), despesa.getValorDespesa(),
				despesa.getDataDespesa(), usuario);

		despesaRepository.save(atualizarDespesa);

	}
	
	public void deletarDespesaPorId(Integer id) {
		despesaRepository.deleteById(id);
	}
	
	public ResponseEntity<?> valorDespesaMesAnoPesquisado(Date data, Integer id) {

		Double valorTotal = 0.0;

		MesAnoDTO mesAnoDTO = datas.retornarAnoEMes(data);

		List<Double> valorResgatado = despesaRepository.valoresDespesaDataAtual(mesAnoDTO.getAno(), mesAnoDTO.getMes(),
				id);

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


//	public ResponseEntity<?> valorDespesaDataAtual(Integer id) {
//
//		Double valorTotal = 0.0;
//
//
//		List<Double> valorResgatado = despesaRepository.valoresDespesaDataAtual(Datas.pegarAnoAtual(),
//				Datas.pegarMesAtual(), id);
//
//		if (valorResgatado.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.SEM_DESPESA_MES_ATUAL);
//		}
//
//		for (int i = 0; i < valorResgatado.size(); i++) {
//			valorTotal += valorResgatado.get(i);
//		}
//
//		return ResponseEntity.ok(valorTotal);
//	}

	public List<String> anosEmQueExisteDespesa(Integer id) {
		return despesaRepository.todosOsAnosQueExistemDespesas(id);
	}

}
