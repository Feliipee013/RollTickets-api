package br.com.RollTickets.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import br.com.RollTickets.api.entity.Assento;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {
    List<Assento> findBySessaoId(Long sessaoId);
    boolean existsByNumeroAndFileiraAndSessaoId(String numero, String fileira, Long sessaoId);
}
