package dev.joaomarcelo.controleFinanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.joaomarcelo.controleFinanceiro.domain.TipoReceita;

@Repository
public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Integer> {

	@Query(value = "SELECT * FROM Tipo_Receita obj WHERE obj.usuario_id=?1 order by obj.descricao ASC", nativeQuery = true)
	List<TipoReceita> findByUsuario(@Param("ano") Integer ano);

}
