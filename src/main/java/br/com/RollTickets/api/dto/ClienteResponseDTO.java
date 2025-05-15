package br.com.RollTickets.api.dto;

public record ClienteResponseDTO(
		long id,
		String nome,
		String email,
		String senha,
		String cpf,
		String telefone
		) {}
