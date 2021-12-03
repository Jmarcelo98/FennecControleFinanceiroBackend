package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import dev.joaomarcelo.controleFinanceiro.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void enviarCodigoSenha(Usuario usuario, String novaSenha) {
		SimpleMailMessage sm = prepararNovaSenhaEmail(usuario, novaSenha);
		enviarEmail(sm);

	}

	protected SimpleMailMessage prepararNovaSenhaEmail(Usuario usuario, String novaSenha) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Olá " + usuario.getNome() + " " + usuario.getSobrenome() + "\n Seu código para criar uma nova senha é: " + novaSenha);
		return sm;
	}

}
