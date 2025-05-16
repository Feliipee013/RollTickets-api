package br.com.RollTickets.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.RollTickets.api.dto.ClienteCreateDTO;
import br.com.RollTickets.api.dto.ClienteResponseDTO;
import br.com.RollTickets.api.dto.ClienteUpdateDTO;
import br.com.RollTickets.api.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteResponseDTO> store(@RequestBody ClienteCreateDTO clienteCreateDTO) {
		return new ResponseEntity<>(clienteService.store(clienteCreateDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> list()
	{
		return new ResponseEntity<>(clienteService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{cliente_id}")
	public ResponseEntity<ClienteResponseDTO> show(@PathVariable long cliente_id) {
		try {
			return new ResponseEntity<>(clienteService.show(cliente_id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<ClienteResponseDTO> update(@RequestBody ClienteUpdateDTO clienteUpdateDTO) {
		try {
			return new ResponseEntity<>(clienteService.update(clienteUpdateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{cliente_id}")
	public ResponseEntity<String> destroy(@PathVariable long cliente_id) {
		try {
			clienteService.destroy(cliente_id);
			return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
