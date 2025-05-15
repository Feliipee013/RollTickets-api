package br.com.RollTickets.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.controller.ClienteCreateDTO;
import br.com.RollTickets.api.dto.ClienteResponseDTO;
import br.com.RollTickets.api.entity.Cliente;
import br.com.RollTickets.api.mapper.ClienteMapper;
import br.com.RollTickets.api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteResponseDTO store(ClienteCreateDTO clienteCreateDTO) {
		Cliente cliente = ClienteMapper.toEntity(clienteCreateDTO);
		clienteRepository.save(cliente);
		return ClienteMapper.toDTO(cliente);
	}
}
