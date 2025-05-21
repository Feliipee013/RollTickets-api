package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;

import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.entity.Sala;

public record SessaoUpdateDTO(
    long id,
    Filme filme, 
    Sala sala,
    int quantidade_ingressos_disponiveis,
    LocalDateTime horario
) {
} 
