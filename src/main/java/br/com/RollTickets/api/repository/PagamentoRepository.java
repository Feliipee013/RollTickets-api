package br.com.RollTickets.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.RollTickets.api.entity.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
    
}
