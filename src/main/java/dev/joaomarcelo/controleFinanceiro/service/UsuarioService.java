package dev.joaomarcelo.controleFinanceiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.joaomarcelo.controleFinanceiro.domain.Receita;
import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.repository.UsuarioRepository;
import dev.joaomarcelo.controleFinanceiro.service.exception.ObjetoNaoEncontrado;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

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
