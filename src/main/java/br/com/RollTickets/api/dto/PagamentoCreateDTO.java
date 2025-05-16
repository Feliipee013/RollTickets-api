package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;

import br.com.RollTickets.api.enums.metodoPagamento;
import br.com.RollTickets.api.enums.status;

public record PagamentoCreateDTO(
    Ingresso ingresso,
    metodoPagamento metodoPagamento,
    status status
) {}
