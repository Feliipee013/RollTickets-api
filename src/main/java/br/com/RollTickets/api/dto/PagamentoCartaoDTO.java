package br.com.RollTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PagamentoCartaoDTO(
    String token,

     Long compraId,

      Long clienteId,

    @JsonProperty("payment_method_id")
    String paymentMethodId,

    @JsonProperty("issuer_id")
    String issuerId,

    Payer payer,

    Integer installments,

    @JsonProperty("transaction_amount")
    Double valor
) {

    public record Payer(
        String email
        // pode adicionar identification se quiser
    ) {}
}
