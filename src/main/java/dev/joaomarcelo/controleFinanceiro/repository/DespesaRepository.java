package dev.joaomarcelo.controleFinanceiro.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.joaomarcelo.controleFinanceiro.domain.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT COUNT(*) FROM Despesa obj WHERE YEAR(obj.data_despesa)=?1 AND MONTH(obj.data_despesa)=?2 AND obj.usuario_id=?3", nativeQuery = true)
	Integer quantidadeDeDespesas(@Param("ano") Integer ano, @Param("mes") Integer mes, @Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT obj.data_despesa FROM Despesa obj WHERE obj.usuario_id=?1 ORDER BY obj.data_despesa DESC LIMIT 1", nativeQuery = true)
	Date buscarDataMaisRecenteDaDespesa(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM Despesa obj WHERE YEAR(obj.data_despesa)=?1 AND MONTH(obj.data_despesa)=?2 AND obj.usuario_id=?3 ORDER BY data_despesa desc ", nativeQuery = true)
	List<Despesa> findDespesaByIdUsuarioPeloMesEAno(@Param("ano") Integer ano, @Param("mes") Integer mes, @Param("id") Integer id,
			Pageable pageRequest);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT obj.valor_despesa FROM Despesa obj WHERE YEAR(obj.data_despesa)=?1 AND MONTH(obj.data_despesa)=?2 AND obj.usuario_id=?3 ORDER BY data_despesa desc ", nativeQuery = true)
	List<Double> valoresDespesaDataAtual(@Param("ano") Integer ano, @Param("mes") Integer mes, @Param("id") Integer id);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM Despesa obj WHERE obj.usuario_id = ? ORDER BY data_despesa desc", nativeQuery = true)
	Optional<List<Despesa>> findByIdUsuario(@Param("id") Integer id);



	

	@Transactional(readOnly = true)
	@Query(value = "SELECT DISTINCT YEAR(obj.DATA_DESPESA) FROM Despesa obj WHERE obj.usuario_id=?1 ORDER BY YEAR(obj.DATA_DESPESA) DESC", nativeQuery = true)
	List<String> todosOsAnosQueExistemDespesas(@Param("id") Integer id);

	@Transactional(readOnly = true)
	@Query(value = "SELECT MONTH(obj.data_despesa), SUM(obj.valor_despesa) FROM Despesa obj WHERE obj.usuario_id = ?1 and YEAR(obj.data_despesa) = ?2 GROUP BY MONTH(obj.data_despesa)", nativeQuery = true)
	List<String> buscarValoresEMesDeAcordoComAno(@Param("id") Integer id, @Param("ano") Integer ano);

	@Transactional(readOnly = true)
	@Query(value = "SELECT SUM(obj.valor_despesa) FROM Despesa obj WHERE YEAR(obj.data_despesa)=?1 AND obj.usuario_id=?2", nativeQuery = true)
	Double valorDespesaAnual(@Param("ano") Integer ano, @Param("id") Integer id);

}
