package dev.joaomarcelo.controleFinanceiro.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.joaomarcelo.controleFinanceiro.model.domain.TipoReceita;

@Repository
public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Integer> {

	@Query(value = "SELECT * FROM Tipo_Receita obj WHERE obj.usuario_id=?1 order by obj.descricao ASC", nativeQuery = true)
	List<TipoReceita> findByUsuario(@Param("ano") Integer ano);

	@Query(value = "SELECT * FROM Tipo_Receita obj WHERE obj.usuario_id=?1 ORDER BY obj.descricao ASC", nativeQuery = true)
	List<TipoReceita> findTipoReceitaPaginacao(@Param("id") Integer id, Pageable pageRequest);

	@Query(value = "SELECT COUNT(*) FROM Tipo_Receita obj WHERE obj.usuario_id=?1", nativeQuery = true)
	Integer contador(@Param("id") Integer id);

	Boolean existsByDescricao(String descricao);

	@Query(value = "SELECT DISTINCT CASE WHEN EXISTS (SELECT * FROM TIPO_RECEITA where DESCRICAO"
			+ " = ?1 and usuario_id = ?2) then 'TRUE' else 'FALSE' END FROM tipo_receita", nativeQuery = true)
	Boolean existsByDescricaoAndUsuarioId(@Param("descricao") String descricao, @Param("id") Integer id);

}
