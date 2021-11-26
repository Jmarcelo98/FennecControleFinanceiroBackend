package dev.joaomarcelo.controleFinanceiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.joaomarcelo.controleFinanceiro.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

//	Optional<Usuario> findByUsuario(String usuario);

	Optional<Usuario> findByEmail(String email);

	Boolean existsByEmail(String email);

	@Query(value = "SELECT user.senha FROM Usuario user WHERE user.email = ?1")
	String existsBySenhaByUser(@Param("email") String email);
	
	@Query(value = "SELECT user.senhaProvisoria FROM Usuario user WHERE user.email = ?1")
	String existsBySenhaProvisoriaByUser(@Param("email") String email);
	

}
