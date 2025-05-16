package br.com.RollTickets.api.dto;

public record SalaUpdateDTO(
		long id,
		long numero,
		Assento asssento,
		long capacidade
		) {}
