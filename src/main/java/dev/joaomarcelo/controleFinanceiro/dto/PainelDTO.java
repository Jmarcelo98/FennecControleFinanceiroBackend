package dev.joaomarcelo.controleFinanceiro.dto;

import java.util.List;

public class PainelDTO {

	private List<PainelDespesaDTO> painelDespesaDTO;
	private List<PainelReceitaDTO> painelReceitaDTO;
	private PainelValoresAnuaisESaldo painelValoresAnuaisESaldo;

	public PainelDTO(List<PainelDespesaDTO> painelDespesaDTO, List<PainelReceitaDTO> painelReceitaDTO,
			PainelValoresAnuaisESaldo painelValoresAnuaisESaldo) {
		super();
		this.painelDespesaDTO = painelDespesaDTO;
		this.painelReceitaDTO = painelReceitaDTO;
		this.painelValoresAnuaisESaldo = painelValoresAnuaisESaldo;
	}

	public List<PainelDespesaDTO> getPainelDespesaDTO() {
		return painelDespesaDTO;
	}

	public void setPainelDespesaDTO(List<PainelDespesaDTO> painelDespesaDTO) {
		this.painelDespesaDTO = painelDespesaDTO;
	}

	public List<PainelReceitaDTO> getPainelReceitaDTO() {
		return painelReceitaDTO;
	}

	public void setPainelReceitaDTO(List<PainelReceitaDTO> painelReceitaDTO) {
		this.painelReceitaDTO = painelReceitaDTO;
	}

	public PainelValoresAnuaisESaldo getPainelValoresAnuaisESaldo() {
		return painelValoresAnuaisESaldo;
	}

	public void setPainelValoresAnuaisESaldo(PainelValoresAnuaisESaldo painelValoresAnuaisESaldo) {
		this.painelValoresAnuaisESaldo = painelValoresAnuaisESaldo;
	}

}
