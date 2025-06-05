package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Sessao;

public record AssentoSessaoCreateDTO(
    Assento assento,
    Sessao sessao,
    boolean reservado
) {}
