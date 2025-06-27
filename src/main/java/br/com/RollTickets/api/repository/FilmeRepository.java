package br.com.RollTickets.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.RollTickets.api.entity.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    boolean existsByTitulo(String titulo); // Para olhar se esse título ja existe

	List<Filme> findByTituloContainingIgnoreCase(String titulo); // o JPA sabe que find é um consulta para buscar dados
    															 // e que Titulo será a coluna para ver
    															 // Containing vai ser o LIKE %..%
    														     //IgnoreCase para ignorar maiusc. e minus. na comparação
}
