package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Assento;

public record SalaCreateDTO(
		long numero,
		Assento asssento,
		long capacidade
		) {}
