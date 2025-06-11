package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Filme;


public record SessaoCreateDTO(
    Filme filme, 
    int quantidade_ingressos_disponiveis) {
} 
