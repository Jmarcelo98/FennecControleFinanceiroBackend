package dev.joaomarcelo.controleFinanceiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joaomarcelo.controleFinanceiro.domain.Usuario;
import dev.joaomarcelo.controleFinanceiro.payload.request.LoginRequest;
import dev.joaomarcelo.controleFinanceiro.payload.request.RegistrarRequest;
import dev.joaomarcelo.controleFinanceiro.payload.response.JwtResponse;
import dev.joaomarcelo.controleFinanceiro.payload.response.MessageResponse;
import dev.joaomarcelo.controleFinanceiro.repository.UsuarioRepository;
import dev.joaomarcelo.controleFinanceiro.security.jwt.JwtUtils;
import dev.joaomarcelo.controleFinanceiro.security.services.UserDetailsImpl;
import dev.joaomarcelo.controleFinanceiro.util.FormatarPalavras;
import dev.joaomarcelo.controleFinanceiro.util.MensagensPersonalizadas;

@RestController
@RequestMapping("/auth/")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder codificador;

	private final ResponseEntity<?> loginIncorreto = ResponseEntity.badRequest()
			.body(new MessageResponse(MensagensPersonalizadas.SENHA_USUARIO_INCORRETO));

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping(value = "login")
	public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest) {

		if (!usuarioRepository.existsByEmail(FormatarPalavras.caixaAlta(loginRequest.getEmail()))) {

			return loginIncorreto;

		} else {

			if (!codificador.matches(loginRequest.getSenha(),
					usuarioRepository.existsBySenhaByUser(FormatarPalavras.caixaAlta(loginRequest.getEmail())))) {
				return loginIncorreto;
			}

		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				FormatarPalavras.caixaAlta(loginRequest.getEmail()), loginRequest.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getNome(), userDetails.getSobrenome(), false));
	}

	@PostMapping(value = "cadastrar")
	public ResponseEntity<?> novoUsuario(@RequestBody @Valid RegistrarRequest signUpRequest) {

		if (usuarioRepository.existsByEmail(FormatarPalavras.caixaAlta(signUpRequest.getEmail()))) {
			return ResponseEntity.badRequest().body(new MessageResponse(MensagensPersonalizadas.USUARIO_EXISTENTE));
		}

		// Create new user's account
		Usuario user = new Usuario(FormatarPalavras.salvarNomeFormatado(signUpRequest.getNome()),
				FormatarPalavras.salvarNomeFormatado(signUpRequest.getSobrenome()),
				FormatarPalavras.caixaAlta(signUpRequest.getEmail()), codificador.encode(signUpRequest.getSenha()),
				null);

		usuarioRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(MensagensPersonalizadas.USUARIO_CADASTRADO_COM_SUCESSO));
	}

//	@PostMapping(path = "recuperar-senha")
//	public ResponseEntity<?> esqueciASenha(@Valid @RequestBody EmailDTO emailDTO) {
//		return autenticacaoService.enviarCodigo(emailDTO.getEmail());
//
//	}
//
//	@GetMapping(path = "{email}/{codigo}")
//	public ResponseEntity<?> verificarCodigo(@PathVariable(value = "email") String email,
//			@PathVariable(value = "codigo") String codigo) {
//
//		return autenticacaoService.verificarCodigo(email, codigo);
//	}
	
//	@PutMapping(value = "nova-senha")
//	public ResponseEntity<?> atualizarSenha(@RequestBody @Valid LoginRequest loginRequest) {
//		return usuarioService.atualizarSenha(loginRequest.getEmail(), loginRequest.getSenha());
//	}


//	@PostMapping(value = "/refresh_token")
//	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
//		UserSS user = UserService.autenticado();
//		String token = jwtUtil.generateToken(user.getUsername());
//		response.addHeader("Authorization", "Bearer " + token);
//		return ResponseEntity.noContent().build();
//	}

}
