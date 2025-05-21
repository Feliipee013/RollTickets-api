package br.com.RollTickets.api.dto;

public record ClienteUpdateDTO(
		long id,
		String nome,
		String email,
		String senha,
		String cpf,
		String telefone
		) {}
