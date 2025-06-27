package br.com.RollTickets.api.dto;

import br.com.RollTickets.api.enums.Role;

public record ClienteLoginDTO(
    String email,
    String senha,
    Role role 
) {
} 
