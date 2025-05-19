package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Sala;

public record AssentoCreateDTO (
    String fileira,
    String numero,
    Sala sala
){}
