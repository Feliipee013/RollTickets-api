package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.RollTickets.api.entity.Ingresso;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long>{

}
