package br.com.RollTickets.api.dto;

public record SalaCreateDTO(
		long numero,
		Assento asssento,
		long capacidade
		) {}
