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

import br.com.RollTickets.api.dto.SalaCreateDTO;
import br.com.RollTickets.api.dto.SalaResponseDTO;
import br.com.RollTickets.api.dto.SalaUpdateDTO;
import br.com.RollTickets.api.service.SalaService;

@RestController
@RequestMapping("/api/salas")
public class SalaController {
	
	@Autowired
	SalaService salaService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<SalaResponseDTO> store(@RequestBody SalaCreateDTO salaCreateDTO) {
		return new ResponseEntity<>(salaService.store(salaCreateDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<SalaResponseDTO>> list()
	{
		return new ResponseEntity<>(salaService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{sala_id}")
	public ResponseEntity<SalaResponseDTO> show(@PathVariable long sala_id) {
		try {
			return new ResponseEntity<>(salaService.show(sala_id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<SalaResponseDTO> update(@RequestBody SalaUpdateDTO salaUpdateDTO) {
		try {
			return new ResponseEntity<>(salaService.update(salaUpdateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{sala_id}")
	public ResponseEntity<String> destroy(@PathVariable long sala_id) {
		try {
			salaService.destroy(sala_id);
			return new ResponseEntity<>("Sala deletada com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
