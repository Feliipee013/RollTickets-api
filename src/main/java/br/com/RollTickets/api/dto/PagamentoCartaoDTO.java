package br.com.RollTickets.api.dto;

public record PagamentoCartaoDTO(
    String token,
    String paymentMethodId,
    String issuerId,
    String email,
    Integer installments,
    Double valor
) {}
