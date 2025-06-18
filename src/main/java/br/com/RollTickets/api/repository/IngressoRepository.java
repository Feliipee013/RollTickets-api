package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.entity.Sessao;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    boolean existsBySessaoAndAssento(Sessao sessao, Assento assento); //Função para verificar se existe uma sessão e um assento
    List<Ingresso> findByClienteId(Long clienteId); //Função para achar os ingressos por um id do cliente
    Optional<Ingresso> findBySessaoAndAssento(Sessao sessao, Assento assento);

}
