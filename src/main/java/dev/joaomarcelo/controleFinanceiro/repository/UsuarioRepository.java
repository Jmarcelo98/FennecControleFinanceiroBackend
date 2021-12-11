package dev.joaomarcelo.controleFinanceiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.joaomarcelo.controleFinanceiro.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);

	Boolean existsByEmail(String email);

	@Transactional(readOnly = true)
	@Query(value = "SELECT user.senha FROM Usuario user WHERE user.email = ?1")
	String existsBySenhaByUser(@Param("email") String email);

	Boolean existsByEmailAndCodigoRecuperacaoSenha(String email, String codigo);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT user.codigoRecuperacaoSenha FROM Usuario user WHERE user.email = ?1")
	String recuperandoCodigoCriptografado(String email);	
	

}
