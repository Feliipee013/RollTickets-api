package br.com.RollTickets.api.dto;


import java.time.LocalDateTime;


import br.com.RollTickets.api.enums.metodoPagamento;
import br.com.RollTickets.api.enums.status;

public record PagamentoCreateDTO(
    Long compra_id,
    metodoPagamento metodoPagamento,
    status status,
    LocalDateTime dataHoraPagamento
) {}
