package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.repository.UsuarioRepository;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class AutenticacaoService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public ResponseEntity<?> enviarNovaSenha(String email) {

		Optional<Usuario> usuario = usuarioService.bucarPeloEmail(email.toUpperCase());

		if (usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.EMAIL_NAO_ENCONTRADO);
		}

		String novaSenhaAleatoria = novaSenha();

		emailService.enviarNovaSenhaEmail(usuario.get(), novaSenhaAleatoria);

		usuario.get().setSenhaProvisoria(bCryptPasswordEncoder.encode(novaSenhaAleatoria));

		usuarioRepository.save(usuario.get());

		return ResponseEntity.status(HttpStatus.OK).body(MensagensPersonalizadas.ENVIADO_RECUPERAR_SENHA);

	}

	private String novaSenha() {
		char[] vet = new char[10];

		for (int i = 0; i < vet.length; i++) {
			vet[i] = caracterAleatorio();
		}
		return new String(vet);
	}

	private char caracterAleatorio() {

		int opt = random.nextInt(3);

		if (opt == 0) { // GERA UM DIGITO
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) { // GERA LETRA MAIUSCULA
			return (char) (random.nextInt(26) + 65);
		} else { // GERA LETRA MINUSCULA
			return (char) (random.nextInt(26) + 97);
		}

	}

}
