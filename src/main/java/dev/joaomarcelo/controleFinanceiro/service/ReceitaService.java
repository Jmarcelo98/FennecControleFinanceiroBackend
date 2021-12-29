package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Receita;
import dev.joaomarcelo.controleFinanceiro.domain.TipoReceita;
import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.dto.MesAnoDTO;
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

	private Datas datas = new Datas();

	public Integer quantidadeDeReceitasMensal(Integer id, Date data) {

		MesAnoDTO mesAnoDTO = datas.retornarAnoEMes(data);

		return receitaRepository.quantidadeDeReceitas(mesAnoDTO.getAno(), mesAnoDTO.getMes(), id);

	}

	public Date buscarDataMaisRecenteDaReceita(Integer id) {

		Date dataReceitaMaisRecente = receitaRepository.buscarDataMaisRecenteDaReceita(id);

		if (dataReceitaMaisRecente == null) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_RECEITA_CADASTRADO);
		}

		return dataReceitaMaisRecente;

	}

	public Date buscarDataMaisAntigaDaReceita(Integer id) {

		Date dataReceitaMaisAntiga = receitaRepository.buscarDataMaisAntigaDaReceita(id);

		if (dataReceitaMaisAntiga == null) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_RECEITA_CADASTRADO);
		}

		return dataReceitaMaisAntiga;

	}

	public List<Receita> buscarTodasAsReceitasMesAno(Integer id, Date data, Integer pagina, Integer linhasPorPagina) {

		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina);

		MesAnoDTO mesAnoDTO = datas.retornarAnoEMes(data);

		List<Receita> listaReceitas = receitaRepository.findReceitaByIdUsuarioPeloMesEAno(mesAnoDTO.getAno(),
				mesAnoDTO.getMes(), id, pageRequest);

		if (listaReceitas.size() == 0) {
			throw new ObjetoNaoEncontrado(MensagensPersonalizadas.SEM_RECEITA);
		} else {
			return listaReceitas;
		}
	}

	public void adicionarReceita(ReceitaDTO receita, Integer idUsuario) {

		receita.setId(null);

		Usuario usuario = usuarioService.buscarPeloId(idUsuario);

		Receita novaReceita = new Receita(null, receita.getNomeReceita(), receita.getValorReceita(),
				receita.getDataReceita(), usuario, new TipoReceita(receita.getTipoReceitaDTO().getId(),
						receita.getTipoReceitaDTO().getDescricao(), usuario));

		receitaRepository.save(novaReceita);
	}

	public void atualizarReceita(ReceitaDTO receita, Integer idUsuario) {

		Usuario usuario = usuarioService.buscarPeloId(idUsuario);

//		Receita atualizarReceita = new Receita(receita.getId(), receita.getNomeReceita(), receita.getValorReceita(),
//				receita.getDataReceita(), usuario, new TipoReceita(receita.getTipoReceitaDTO().getId(),
//						receita.getTipoReceitaDTO().getDescricao(), usuario));

		receitaRepository.save(new Receita(receita.getId(), receita.getNomeReceita(), receita.getValorReceita(),
				receita.getDataReceita(), usuario, new TipoReceita(receita.getTipoReceitaDTO().getId(),
						receita.getTipoReceitaDTO().getDescricao(), usuario)));

	}

	public void deletarReceitaPorId(Integer id) {
		receitaRepository.deleteById(id);
	}

	public ResponseEntity<?> valorReceitaMesAnoPesquisado(Date data, Integer id) {

		Double valorTotal = 0.0;

		MesAnoDTO mesAnoDTO = datas.retornarAnoEMes(data);

		List<Double> valorResgatado = receitaRepository.valoresReceitaDataAtual(mesAnoDTO.getAno(), mesAnoDTO.getMes(),
				id);

		if (valorResgatado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.SEM_RECEITA_MES_ATUAL);
		}

		for (int i = 0; i < valorResgatado.size(); i++) {
			valorTotal += valorResgatado.get(i);
		}

		return ResponseEntity.ok(valorTotal);
	}

	// VALOR DA RECEITA NA DATA ATUAL
//	public ResponseEntity<?> valorReceitaDataAtual(Integer id) {
//
//		Double valorTotal = 0.0;
//
//		List<Double> valorResgatado = receitaRepository.valoresReceitaDataAtual(Datas.pegarAnoAtual(),
//				Datas.pegarMesAtual(), id);
//
//		if (valorResgatado.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.SEM_RECEITA_MES_ATUAL);
//		}
//
//		for (int i = 0; i < valorResgatado.size(); i++) {
//			valorTotal += valorResgatado.get(i);
//		}
//
//		return ResponseEntity.ok(valorTotal);
//	}

	public List<String> anosEmQueExisteReceita(Integer id) {
		return receitaRepository.todosOsAnosQueExistemReceitas(id);
	}

//	private MesAnoDTO retornarAnoEMes(Date data) {
//
//		Calendar cal = Calendar.getInstance();
//
//		cal.setTime(data);
//
//		MesAnoDTO mesAnoDTO = new MesAnoDTO(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
//
//		return mesAnoDTO;
//	}

}
