package dev.joaomarcelo.controleFinanceiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import dev.joaomarcelo.controleFinanceiro.service.DBService;

@Configuration
@Profile("test")
public class TesteConfig {

	@Autowired
	private DBService DBService;

	@Bean
	public boolean instanciarBancoDeDados() {
		DBService.instaciarTesteBancoDeDados();
		return true;
	}

}
