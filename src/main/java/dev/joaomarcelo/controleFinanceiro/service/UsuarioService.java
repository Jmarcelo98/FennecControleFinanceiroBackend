package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Receita;
import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.repository.UsuarioRepository;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;
import dev.joaomarcelo.controleFinanceiro.util.FormatarPalavras;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder codificador;

	public ResponseEntity<?> atualizarSenha(String email, String senha) {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(FormatarPalavras.caixaAlta(email));

		if (!usuario.isEmpty()) {
			usuario.get().setSenha(codificador.encode(senha));
			usuario.get().setCodigoRecuperacaoSenha(null);
			usuarioRepository.save(usuario.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MensagensPersonalizadas.ERROR_DESCONHECIDO);
		}

	}

	public Usuario buscarPeloId(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontrado(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Receita.class.getName()));
	}

	public Boolean existePeloEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	public Optional<Usuario> bucarPeloEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
