package br.com.RollTickets.api.dto;



import java.util.*;


public record CompraCreateDTO( Long clienteId, List<Long> ingresso ,Long pagamentoId) {
}
