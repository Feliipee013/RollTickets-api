package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.RollTickets.api.entity.Ingresso;

@NoRepositoryBean
public interface IngressoRepository extends JpaRepository<Ingresso, Long>{

}
