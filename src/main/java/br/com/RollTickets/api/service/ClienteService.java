package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.ClienteCreateDTO;
import br.com.RollTickets.api.dto.ClienteResponseDTO;
import br.com.RollTickets.api.dto.ClienteUpdateDTO;
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
	
	public List<ClienteResponseDTO> list() {
		return clienteRepository.findAll().stream().map(ClienteMapper::toDTO).toList();
	}
	
	public ClienteResponseDTO show(long id) {
			Cliente cliente = clienteRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Cliente com id" + id + " não encontrado"));
			return ClienteMapper.toDTO(cliente);
	}
	
	public ClienteResponseDTO update(ClienteUpdateDTO clienteUpdateDTO) {
		Cliente cliente = clienteRepository.findById(clienteUpdateDTO.id()).orElseThrow(()->new RuntimeException("Cliente não encontrado para alteração"));
		cliente.setCpf(clienteUpdateDTO.cpf());
		cliente.setEmail(clienteUpdateDTO.email());
		cliente.setNome(clienteUpdateDTO.nome());
		cliente.setSenha(clienteUpdateDTO.senha());
		cliente.setTelefone(clienteUpdateDTO.telefone());
		return ClienteMapper.toDTO(clienteRepository.save(cliente));
	}
	
	public void destroy(long id) {
		Cliente cliente= clienteRepository.findById(id).orElseThrow(()->new RuntimeException("Cliente não encontrado para deleção"));
		clienteRepository.delete(cliente);
	}
}
