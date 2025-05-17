package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Assento;

public record SalaResponseDTO(
		long id,
		long numero,
		Assento asssento,
		long capacidade
		) {}
