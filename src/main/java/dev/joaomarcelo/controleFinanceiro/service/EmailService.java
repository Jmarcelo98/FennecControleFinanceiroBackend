package dev.joaomarcelo.controleFinanceiro.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import dev.joaomarcelo.controleFinanceiro.domain.Usuario;

public interface EmailService {
	
	void enviarCodigoRecuperacaoSenha(Usuario usuario, String novaSenha);
	
	void enviarEmail(SimpleMailMessage msg); 
	
	void enviarHtmlEmail(MimeMessage msg);
	
}
