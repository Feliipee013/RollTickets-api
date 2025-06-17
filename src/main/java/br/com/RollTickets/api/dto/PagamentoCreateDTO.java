package br.com.RollTickets.api.dto;


import br.com.RollTickets.api.entity.Compra;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.enums.metodoPagamento;
import br.com.RollTickets.api.enums.status;

public record PagamentoCreateDTO(
    Compra compra,
    metodoPagamento metodoPagamento,
    status status
) {}
