package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Assento;

public record SalaUpdateDTO(
		long id,
		long numero,
		Assento assento,
		long capacidade
		) {}
