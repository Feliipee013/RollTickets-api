package br.com.RollTickets.api.dto;

public record SalaResponseDTO(
		long id,
		long numero,
		Assento asssento,
		long capacidade
		) {}
