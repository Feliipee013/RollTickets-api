package br.com.RollTickets.api.repository;
import org.springframework.stereotype.Repository;

import br.com.RollTickets.api.entity.Sessao;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long>{

    
}
