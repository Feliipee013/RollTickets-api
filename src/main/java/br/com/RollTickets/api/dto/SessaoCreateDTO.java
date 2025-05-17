package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.entity.Sala;

public record SessaoCreateDTO(
    Filme filme, 
    Sala sala,
    int quantidade_ingressos_disponiveis) {
} 
