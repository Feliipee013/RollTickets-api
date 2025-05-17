package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.RollTickets.api.entity.Ingresso2D;

@Repository
public interface Ingresso2DRepository extends JpaRepository<Ingresso2D, Long>{

}