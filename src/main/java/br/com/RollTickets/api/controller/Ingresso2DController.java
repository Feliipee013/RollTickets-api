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

import br.com.RollTickets.api.dto.Ingresso2DCreateDTO;
import br.com.RollTickets.api.dto.Ingresso2DResponseDTO;
import br.com.RollTickets.api.dto.Ingresso2DUpdateDTO;
import br.com.RollTickets.api.service.Ingresso2DService;

@RestController
@RequestMapping("/api/ingressos_2D")
public class Ingresso2DController {
	@Autowired
	Ingresso2DService ingresso2DService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Ingresso2DResponseDTO> store(@RequestBody Ingresso2DCreateDTO ingresso2DCreateDTO) {
		return new ResponseEntity<>(ingresso2DService.store(ingresso2DCreateDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Ingresso2DResponseDTO>> list()
	{
		return new ResponseEntity<>(ingresso2DService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{ingresso2D_id}")
	public ResponseEntity<Ingresso2DResponseDTO> show(@PathVariable long ingresso2D_id) {
		try {
			return new ResponseEntity<>(ingresso2DService.show(ingresso2D_id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<Ingresso2DResponseDTO> update(@RequestBody Ingresso2DUpdateDTO ingresso2DUpdateDTO) {
		try {
			return new ResponseEntity<>(ingresso2DService.update(ingresso2DUpdateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{ingresso2D_id}")
	public ResponseEntity<String> destroy(@PathVariable long ingresso2D_id) {
		try {
			ingresso2DService.destroy(ingresso2D_id);
			return new ResponseEntity<>("Ingresso2D deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
