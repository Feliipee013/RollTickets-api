package br.com.RollTickets.api.dto;

public record SalaResponseDTO(
		long id,
		long numero,
		A asssento,
		long capacidade
		) {}
