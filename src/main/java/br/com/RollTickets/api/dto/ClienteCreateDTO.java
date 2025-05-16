package br.com.RollTickets.api.dto;

public record ClienteCreateDTO(
		String nome,
		String email,
		String senha,
		String cpf,
		String telefone
		) {}
