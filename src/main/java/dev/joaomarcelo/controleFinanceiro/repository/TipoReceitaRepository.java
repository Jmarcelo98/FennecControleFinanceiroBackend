package dev.joaomarcelo.controleFinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.joaomarcelo.controleFinanceiro.domain.TipoReceita;

@Repository
public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Integer> {

}
