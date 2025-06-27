package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.ClienteCreateDTO;
import br.com.RollTickets.api.dto.ClienteResponseDTO;
import br.com.RollTickets.api.entity.Cliente;
import br.com.RollTickets.api.enums.Role;

public class ClienteMapper {

	public static ClienteResponseDTO toDTO(Cliente cliente) {
		ClienteResponseDTO clienteResponse = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getCpf(), cliente.getTelefone(),cliente.getRole());
		return clienteResponse;
	}
	
	public static Cliente toEntity(ClienteCreateDTO clienteCreateDTO) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteCreateDTO.nome());
		cliente.setEmail(clienteCreateDTO.email());
		cliente.setSenha(clienteCreateDTO.senha());
		cliente.setCpf(clienteCreateDTO.cpf());
		cliente.setTelefone(clienteCreateDTO.telefone());
		cliente.setRole(Role.USER);
		return cliente;
	}	
}
