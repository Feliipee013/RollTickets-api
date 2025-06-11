package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;

import br.com.RollTickets.api.entity.Filme;


public record SessaoResponseDTO(
    long id,
    Filme filme, 
    int quantidade_ingressos_disponiveis,
    LocalDateTime horario
) {
}
