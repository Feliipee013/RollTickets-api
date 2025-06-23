package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.entity.Sessao;
import br.com.RollTickets.api.enums.status;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    boolean existsBySessaoAndAssento(Sessao sessao, Assento assento); //Função para verificar se existe uma sessão e um assento
    List<Ingresso> findByClienteId(Long clienteId); //Função para achar os ingressos por um id do cliente
    Optional<Ingresso> findBySessaoAndAssento(Sessao sessao, Assento assento);
    Optional<Ingresso> findByAssentoId(Long assentoId);
    
    @Query("""
    	    SELECT i FROM Ingresso i
    	    JOIN i.compra c
    	    JOIN Pagamento p ON p.compra = c
    	    WHERE i.cliente.id = :clienteId AND p.status = :status
    	""")
    	List<Ingresso> findByClienteIdAndPagamentoStatus(@Param("clienteId") Long clienteId,
    	                                                 @Param("status") status status);
}
