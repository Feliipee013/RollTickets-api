package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Cliente;

public record Ingresso3DResponseDTO(
		long id,
		double preco,
		Sessao sessao,
		Assento assento,
		Cliente cliente,
		boolean incluiOculos,
		double taxaExtra3D
		) {}
