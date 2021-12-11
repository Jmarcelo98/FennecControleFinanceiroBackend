package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Despesa;
import dev.joaomarcelo.controleFinanceiro.domain.Receita;
import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.repository.DespesaRepository;
import dev.joaomarcelo.controleFinanceiro.repository.ReceitaRepository;
import dev.joaomarcelo.controleFinanceiro.repository.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private BCryptPasswordEncoder codificador;

	private Calendar cal = Calendar.getInstance();
	private Calendar cal2 = Calendar.getInstance();
	private Calendar cal3 = Calendar.getInstance();
	private Calendar cal4 = Calendar.getInstance();
	private Calendar cal5 = Calendar.getInstance();
	private Calendar cal6 = Calendar.getInstance();
	private Calendar cal7 = Calendar.getInstance();
	private Calendar cal8 = Calendar.getInstance();
	private Calendar cal9 = Calendar.getInstance();
	private Calendar cal10 = Calendar.getInstance();
	private Calendar cal11 = Calendar.getInstance();
	private Calendar cal12 = Calendar.getInstance();
	private Calendar cal13 = Calendar.getInstance();
	private Calendar cal14 = Calendar.getInstance();
	private Calendar cal15 = Calendar.getInstance();
	private Calendar cal16 = Calendar.getInstance();
	private Calendar cal17 = Calendar.getInstance();
	private Calendar cal18 = Calendar.getInstance();
	private Calendar cal19 = Calendar.getInstance();
	private Calendar cal20 = Calendar.getInstance();
	private Calendar cal21 = Calendar.getInstance();
	private Calendar cal22 = Calendar.getInstance();
	private Calendar cal23 = Calendar.getInstance();
	private Calendar cal24 = Calendar.getInstance();
	private Calendar cal25 = Calendar.getInstance();
	private Calendar cal26 = Calendar.getInstance();
	

	public void instaciarTesteBancoDeDados() {

		cal.set(2017, 02, 10, 7, 31, 33);
		cal3.set(2021, 8, 6, 4, 12, 42);
		cal5.set(2020, 11, 12, 10, 32, 22);

		cal2.set(2021, 11, 16, 10, 32, 22);
		cal4.set(2020, 10, 10, 10, 32, 22);
		cal6.set(2020, 10, 05, 15, 12, 26);
		cal7.set(2021, 8, 05, 15, 12, 26);
		cal8.set(2021, 8, 25, 10, 2, 26);

		cal9.set(2016, 10, 14, 10, 32, 22);

		cal10.set(2021, 1, 1, 10, 32, 22);
		cal11.set(2021, 2, 1, 10, 32, 22);
		cal12.set(2021, 3, 1, 10, 32, 22);
		cal13.set(2021, 4, 1, 10, 32, 22);
		cal14.set(2021, 5, 1, 10, 32, 22);
		cal15.set(2021, 6, 1, 10, 32, 22);
		cal16.set(2021, 7, 1, 10, 32, 22);
		cal17.set(2021, 8, 1, 10, 32, 22);
		cal18.set(2021, 9, 1, 10, 32, 22);
		cal19.set(2021, 10, 1, 10, 32, 22);
		cal20.set(2021, 11, 7, 0, 0, 1);
		cal21.set(2021, 11, 1, 0, 0, 1);
		cal22.set(2021, 11, 4, 0, 0, 1);
		cal23.set(2021, 11, 4, 0, 0, 1);
		cal24.set(2021, 11, 2, 0, 0, 1);
		cal25.set(2021, 11, 8, 0, 0, 1);
		cal26.set(2021, 11, 9, 0, 0, 1);
		
		cal21.set(2020, 12, 10, 0, 0, 1);
		

		// USUARIO
		Usuario usuario1 = new Usuario("João", "Marcelo", "JOAOMARCELO588@GMAIL.COM", codificador.encode("123456"),
				null);

		Usuario usuario2 = new Usuario("Matheus", "Sena", "SENAVS@GMAIL.COM", codificador.encode("123456"), null);

		Usuario usuario3 = new Usuario("Teste", "Teste2", "TESTE@GMAIL.COM", codificador.encode("123456"), null);

		usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3));

		// DESPESA

		Despesa despesa1 = new Despesa(null, "Jogos", 240.50, cal.getTime(), usuario1);
		Despesa despesa2 = new Despesa(null, "McDonald's", 50.50, cal3.getTime(), usuario1);
		Despesa despesa3 = new Despesa(null, "Teste", 30.50, cal3.getTime(), usuario1);
		Despesa despesa4 = new Despesa(null, "Placa de vídeo", 3000.0, cal5.getTime(), usuario1);
		Despesa despesa5 = new Despesa(null, "Placa solar", 8000.0, new Date(), usuario1);
		Despesa despesa6 = new Despesa(null, "Fonte", 300.0, new Date(), usuario1);
		Despesa despesa7 = new Despesa(null, "Ram", 100.0, new Date(), usuario1);
		Despesa despesa13 = new Despesa(null, "Monitor", 1600.0, cal11.getTime(), usuario1);
		Despesa despesa14 = new Despesa(null, "Copo", 200.0, cal13.getTime(), usuario1);
		Despesa despesa15 = new Despesa(null, "Fio do controle", 370.0, cal15.getTime(), usuario1);
		Despesa despesa16 = new Despesa(null, "Cabo do monitor", 400.0, cal20.getTime(), usuario1);
		Despesa despesa17 = new Despesa(null, "Memória Ram", 630.0, cal17.getTime(), usuario1);
		Despesa despesa18 = new Despesa(null, "Cadeira gamer", 3800.0, cal21.getTime(), usuario1);
		Despesa despesa19 = new Despesa(null, "Shows", 500.0, cal12.getTime(), usuario1);
		Despesa despesa20 = new Despesa(null, "Bebidas", 1000.0, cal14.getTime(), usuario1);
		Despesa despesa21 = new Despesa(null, "Comidas", 250.0, cal16.getTime(), usuario1);
		Despesa despesa22 = new Despesa(null, "Presente", 1000.0, cal18.getTime(), usuario1);

		Despesa despesa8 = new Despesa(null, "Processador", 1340.50, cal2.getTime(), usuario2);
		Despesa despesa9 = new Despesa(null, "Cinema", 50.50, cal4.getTime(), usuario2);
		Despesa despesa10 = new Despesa(null, "Plano móvel", 60.0, cal6.getTime(), usuario2);
		Despesa despesa11 = new Despesa(null, "Plano OI FIBRA", 100.0, cal7.getTime(), usuario2);
		Despesa despesa12 = new Despesa(null, "Cadeira gamer", 800.0, cal8.getTime(), usuario2);

		despesaRepository.saveAll(Arrays.asList(despesa1, despesa2, despesa3, despesa4, despesa5, despesa6, despesa7,
				despesa8, despesa9, despesa10, despesa11, despesa12, despesa13, despesa14, despesa16, despesa15,
				despesa17, despesa18, despesa19, despesa20, despesa21, despesa22));

		// RECEITA

		Receita receita1 = new Receita(null, "Investimento", 400.20, cal.getTime(), usuario1);
		Receita receita2 = new Receita(null, "Presente de aniversário", 200.0, cal3.getTime(), usuario1);
		Receita receita3 = new Receita(null, "Freelancer", 2200.0, cal5.getTime(), usuario1);
		Receita receita4 = new Receita(null, "Bônus", 1200.0, new Date(), usuario1);
		Receita receita5 = new Receita(null, "Beneficios", 2200.0, new Date(), usuario1);
		Receita receita6 = new Receita(null, "IR", 2800.0, cal3.getTime(), usuario1);
		Receita receita12 = new Receita(null, "Lavar carro", 30.0, cal3.getTime(), usuario1);
		Receita receita13 = new Receita(null, "Venda de monitor", 800.0, cal3.getTime(), usuario1);
		Receita receita14 = new Receita(null, "Venda do Computador", 5800.0, cal10.getTime(), usuario1);
		Receita receita15 = new Receita(null, "Venda na OLX", 350.0, cal11.getTime(), usuario1);
		Receita receita16 = new Receita(null, "Freelancer", 1800.0, cal12.getTime(), usuario1);
		Receita receita17 = new Receita(null, "Bonificação", 800.0, cal13.getTime(), usuario1);
		Receita receita18 = new Receita(null, "Décimo terceiro", 2850.0, cal14.getTime(), usuario1);
		Receita receita19 = new Receita(null, "Venda de cadeira", 760.0, cal15.getTime(), usuario1);
		Receita receita24 = new Receita(null, "Venda de teclado antigo", 270.0, cal15.getTime(), usuario1);
		Receita receita25 = new Receita(null, "Venda do copo Stanley", 200.0, cal15.getTime(), usuario1);
		Receita receita20 = new Receita(null, "Venda de monitor secundário", 1300.0, cal16.getTime(), usuario1);
		Receita receita21 = new Receita(null, "Venda de controle", 140.0, cal18.getTime(), usuario1);
		Receita receita22 = new Receita(null, "Cashback", 325.0, cal19.getTime(), usuario1);
		Receita receita23 = new Receita(null, "Venda de Teclado Redragon", 2310.0, cal20.getTime(), usuario1);
		Receita receita31 = new Receita(null, "Freelancer", 2200.0, cal21.getTime(), usuario1);
		Receita receita30 = new Receita(null, "CSGORoll", 300.0, cal21.getTime(), usuario1);
		Receita receita26 = new Receita(null, "VAMOS GG", 1500.0, cal23.getTime(), usuario1);
		Receita receita27 = new Receita(null, "Refeição vendido", 200.0, cal24.getTime(), usuario1);
		Receita receita28 = new Receita(null, "Lavagem de carro", 20.0, cal25.getTime(), usuario1);
		Receita receita29 = new Receita(null, "Recebido da Tauany", 254.23, cal26.getTime(), usuario1);
		Receita receita32 = new Receita(null, "Décimo terceiro", 800.0, new Date(), usuario1);
		
		Receita receita7 = new Receita(null, "Salário", 6500.00, cal7.getTime(), usuario2);
		Receita receita8 = new Receita(null, "Venda do alimentação", 650.0, cal4.getTime(), usuario2);
		Receita receita9 = new Receita(null, "Bônus do plantão", 260.0, cal6.getTime(), usuario2);
		Receita receita10 = new Receita(null, "Teste1", 460.0, cal7.getTime(), usuario2);
		Receita receita11 = new Receita(null, "Teste 2", 1460.0, cal19.getTime(), usuario2);

		receitaRepository.saveAll(Arrays.asList(receita1, receita2, receita3, receita4, receita5, receita6, receita7,
				receita8, receita9, receita10, receita11, receita12, receita13, receita14, receita15, receita16,
				receita17, receita18, receita19, receita20, receita21, receita22, receita23, receita24, receita25,
				receita26, receita27, receita28, receita29, receita30, receita31, receita32));

	}

}
