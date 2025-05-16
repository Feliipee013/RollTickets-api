package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.RollTickets.api.entity.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
