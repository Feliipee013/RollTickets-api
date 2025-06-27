package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.enums.Role;

public record ClienteResponseDTO(
		long id,
		String nome,
		String email,
		String senha,
		String cpf,
		String telefone,
		Role role
		) {}
