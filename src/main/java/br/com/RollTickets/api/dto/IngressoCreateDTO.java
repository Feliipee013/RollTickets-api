package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Cliente;
import br.com.RollTickets.api.entity.Sessao;

public record IngressoCreateDTO(
		String tipo,
		double preco,
		Long sessaoid, //Coloquei isso para o front não precisar passar o objeto inteiro, no caso vamos associar só com o id
		Long assentoid,
		Long clienteid,
		Boolean incluiOculos,
		Double taxaExtra3D
		) {}
