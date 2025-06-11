package br.com.RollTickets.api.repository;
import org.springframework.stereotype.Repository;

import br.com.RollTickets.api.entity.Sessao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long>{
	List<Sessao> findByFilmeId(Long filmeId);
}
