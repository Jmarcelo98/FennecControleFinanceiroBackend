package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.repository.UsuarioRepository;
import dev.joaomarcelo.controleFinanceiro.util.FormatarPalavras;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class AutenticacaoService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public ResponseEntity<?> enviarCodigo(String email) {

		Optional<Usuario> usuario = usuarioService.bucarPeloEmail(FormatarPalavras.caixaAlta(email).trim());

		if (usuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensagensPersonalizadas.EMAIL_NAO_ENCONTRADO);
		}

		String novaSenhaAleatoria = novaSenha();

		emailService.enviarCodigoSenha(usuario.get(), novaSenhaAleatoria);

		usuario.get().setCodigoRecuperacaoSenha(novaSenhaAleatoria);

		usuarioRepository.save(usuario.get());

		return ResponseEntity.noContent().build();

	}

	public ResponseEntity<?> verificarCodigo(String email, String codigo) {

		if (usuarioRepository.existsByEmailAndCodigoRecuperacaoSenha(FormatarPalavras.caixaAlta(email).trim(),
				codigo)) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MensagensPersonalizadas.CODIGO_INVALIDO);
		}

	}

	private String novaSenha() {
		char[] vet = new char[6];

		for (int i = 0; i < vet.length; i++) {
			vet[i] = caracterAleatorio();
		}
		return new String(vet);
	}

	private char caracterAleatorio() {

		int opt = random.nextInt(2);

		if (opt == 0) { // GERA UM DIGITO
			return (char) (random.nextInt(10) + 48);
		} else { // GERA LETRA MAIUSCULA
			return (char) (random.nextInt(26) + 65);
		}

	}

}
