package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Sala;
import br.com.RollTickets.api.entity.Sessao;

public record AssentoResponseDTO (
    long id,
    String fileira,
    String numero,
    Sala sala,
    Sessao sessao
){}
    

