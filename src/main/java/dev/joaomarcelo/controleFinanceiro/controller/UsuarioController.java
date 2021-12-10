package dev.joaomarcelo.controleFinanceiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.dto.EmailDTO;
import dev.joaomarcelo.controleFinanceiro.payload.request.LoginRequest;
import dev.joaomarcelo.controleFinanceiro.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

//	@GetMapping(value = "/existe")
//	public Boolean existePeloEmail(@RequestParam("email") String email) {
//		return usuarioService.existePeloEmail(email);
//	}

	@PutMapping(path = "/nova-senha")
	public ResponseEntity<?> atualizarSenha(@RequestBody @Valid LoginRequest loginRequest) {
		return usuarioService.atualizarSenha(loginRequest.getEmail(), loginRequest.getSenha());
	}

	@PostMapping(path = "/enviar-codigo-email")
	public ResponseEntity<?> enviarCodigoEmail(@Valid @RequestBody EmailDTO emailDTO) {
		return usuarioService.enviarCodigo(emailDTO.getEmail());

	}

	@GetMapping(path = "/verificarCodigo")
	public ResponseEntity<?> verificarCodigoEnviado(@RequestParam String email, @RequestParam String codigo) {
		return usuarioService.verificarCodigo(email, codigo);
	}

}
