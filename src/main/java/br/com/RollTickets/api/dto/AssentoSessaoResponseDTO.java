package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Sessao;

public record AssentoSessaoResponseDTO(
        long id,
        Assento assento,
        Sessao sessao,
        boolean reservado) {
}
