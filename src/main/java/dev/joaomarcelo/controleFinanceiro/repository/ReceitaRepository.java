package dev.joaomarcelo.controleFinanceiro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.joaomarcelo.controleFinanceiro.domain.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

//	@Transactional(readOnly = true)
//	@Query(value = "SELECT * FROM Receita obj WHERE obj.usuario = ? ORDER BY dataReceita desc")
//	Optional<List<Receita>> findByIdUsuario(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM Receita obj WHERE YEAR(obj.data_receita)=?1 AND MONTH(obj.data_receita)=?2 AND obj.usuario_id=?3 ORDER BY obj.data_receita desc, obj.valor_receita DESC, obj.nome_receita ASC ", nativeQuery = true)
	List<Receita> findReceitaByIdUsuarioPeloMesEAno(@Param("ano") Integer ano, @Param("mes") Integer mes,
			@Param("id") Integer id, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query(value = "SELECT COUNT(*) FROM Receita obj WHERE YEAR(obj.data_receita)=?1 AND MONTH(obj.data_receita)=?2 AND obj.usuario_id=?3", nativeQuery = true)
	Integer quantidadeDeReceitas(@Param("ano") Integer ano, @Param("mes") Integer mes, @Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT obj.valor_receita FROM Receita obj WHERE YEAR(obj.data_receita)=?1 AND MONTH(obj.data_receita)=?2 AND obj.usuario_id=?3 ORDER BY obj.data_receita desc ", nativeQuery = true)
	List<Double> valoresReceitaDataAtual(@Param("ano") Integer ano, @Param("mes") Integer mes, @Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT YEAR(obj.DATA_RECEITA) FROM Receita obj WHERE obj.usuario_id=?1 ORDER BY YEAR(obj.DATA_RECEITA) DESC", nativeQuery = true)
	List<String> todosOsAnosQueExistemReceitas(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM Receita obj WHERE obj.usuario_id = ?", nativeQuery = true)
	List<Receita> findAllById(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT MONTH(obj.data_receita), SUM(obj.valor_receita) FROM Receita obj WHERE obj.usuario_id = ?1 and YEAR(obj.data_receita) = ?2 GROUP BY MONTH(obj.data_receita)", nativeQuery = true)
	List<String> buscarValoresEMesDeAcordoComAno(@Param("id") Integer id, @Param("ano") Integer ano);

	@Transactional(readOnly = true)
	@Query(value = "SELECT SUM(obj.valor_receita) FROM Receita obj WHERE YEAR(obj.data_receita)=?1 AND obj.usuario_id=?2", nativeQuery = true)
	Double valorReceitaAnual(@Param("ano") Integer ano, @Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT obj.data_receita FROM Receita obj WHERE obj.usuario_id=?1 ORDER BY obj.data_receita DESC LIMIT 1", nativeQuery = true)
	Date buscarDataMaisRecenteDaReceita(@Param("id") Integer id);

}
