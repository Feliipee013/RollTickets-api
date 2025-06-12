package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;

import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.entity.Sala;


public record SessaoCreateDTO(
    Filme filme,
    Sala sala,
    LocalDateTime horario) {
} 
