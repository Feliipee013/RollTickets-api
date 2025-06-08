package br.com.RollTickets.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import br.com.RollTickets.api.entity.AssentoSessao;

public interface AssentoSessaoRepository extends JpaRepository<AssentoSessao, Long> {
    List<AssentoSessao> findBySessaoId(Long sessaoId); //Retorna todos os assentos de uma sesão
    Optional<AssentoSessao> findBySessaoIdAndAssentoId(Long sessaoId, Long assentoId); //Olha se alguém já resevou esse assento


}