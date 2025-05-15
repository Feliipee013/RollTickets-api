package br.com.RollTickets.api.controller;

public record ClienteCreateDTO(
		String nome,
		String email,
		String senha,
		String cpf,
		String telefone
		) {}
