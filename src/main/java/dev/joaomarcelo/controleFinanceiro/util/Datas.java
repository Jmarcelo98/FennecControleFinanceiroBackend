package dev.joaomarcelo.controleFinanceiro.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dev.joaomarcelo.controleFinanceiro.dto.MesAnoDTO;

public class Datas {

	static Date data = new Date();
	static GregorianCalendar dataCal = new GregorianCalendar();

	public static int pegarMesAtual() {

		dataCal.setTime(data);
		int mes = dataCal.get(Calendar.MONTH) + 1;

		return mes;
	}

	public static int pegarAnoAtual() {

		dataCal.setTime(data);
		int ano = dataCal.get(Calendar.YEAR);
		return ano;

	}

	public MesAnoDTO retornarAnoEMes(Date data) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(data);

		MesAnoDTO mesAnoDTO = new MesAnoDTO(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));

		return mesAnoDTO;
	}

}
