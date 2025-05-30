package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Cliente;
import br.com.RollTickets.api.entity.Sessao;

public record IngressoCreateDTO(
		String tipo,
		double preco,
		Sessao sessao,
		Assento assento,
		Cliente cliente,
		Boolean incluiOculos,
		Double taxaExtra3D
		) {}
