package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Cliente;

public record Ingresso2DUpdateDTO(
		long id,
		double preco,
		Sessao sessao,
		Assento assento,
		Cliente cliente
		) {}
