package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.RollTickets.api.entity.Ingresso3D;

@Repository
public interface Ingresso3DRepository extends JpaRepository<Ingresso3D, Long> {

}
