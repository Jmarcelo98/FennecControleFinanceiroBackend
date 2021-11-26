package dev.joaomarcelo.controleFinanceiro.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import dev.joaomarcelo.controleFinanceiro.service.DBService;
import dev.joaomarcelo.controleFinanceiro.service.EmailService;
import dev.joaomarcelo.controleFinanceiro.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String estrategia;

	@Bean
	public boolean instaciarBancoDeDados() throws ParseException {

		if (!"create".equals(estrategia)) {
			return false;
		}
		dbService.instaciarTesteBancoDeDados();

		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}