package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;

import br.com.RollTickets.api.entity.Compra;
import br.com.RollTickets.api.enums.metodoPagamento;
import br.com.RollTickets.api.enums.status;

public record PagamentoUpdateDTO( 
    long id,
    Compra compra,
    metodoPagamento metodoPagamento,
    status status,
    LocalDateTime dataHoraPagamento) {
}