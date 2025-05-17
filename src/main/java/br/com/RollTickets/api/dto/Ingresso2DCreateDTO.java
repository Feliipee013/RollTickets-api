package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Cliente;

public record Ingresso2DCreateDTO(
		double preco,
		Sessao sessao,
		Assento assento,
		Cliente cliente
		) {}
