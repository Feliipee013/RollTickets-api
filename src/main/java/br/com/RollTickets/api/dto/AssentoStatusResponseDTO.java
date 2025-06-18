package br.com.RollTickets.api.dto;

public record AssentoStatusResponseDTO(
    long id,
    String fileira,
    String numero,
    Long sala,
    Long sessao,
    String statusPagamento  // Exemplo: "PAGO", "PENDENTE", "LIVRE"
){}
