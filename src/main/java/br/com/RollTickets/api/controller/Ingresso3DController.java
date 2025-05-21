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

import br.com.RollTickets.api.dto.Ingresso3DCreateDTO;
import br.com.RollTickets.api.dto.Ingresso3DResponseDTO;
import br.com.RollTickets.api.dto.Ingresso3DUpdateDTO;
import br.com.RollTickets.api.service.Ingresso3DService;

@RestController
@RequestMapping("/api/ingressos_3D")
public class Ingresso3DController {
	@Autowired
	Ingresso3DService ingresso3DService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Ingresso3DResponseDTO> store(@RequestBody Ingresso3DCreateDTO ingresso3DCreateDTO) {
		return new ResponseEntity<>(ingresso3DService.store(ingresso3DCreateDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Ingresso3DResponseDTO>> list()
	{
		return new ResponseEntity<>(ingresso3DService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{ingresso3D_id}")
	public ResponseEntity<Ingresso3DResponseDTO> show(@PathVariable long ingresso3D_id) {
		try {
			return new ResponseEntity<>(ingresso3DService.show(ingresso3D_id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<Ingresso3DResponseDTO> update(@RequestBody Ingresso3DUpdateDTO ingresso3DUpdateDTO) {
		try {
			return new ResponseEntity<>(ingresso3DService.update(ingresso3DUpdateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{ingresso3D_id}")
	public ResponseEntity<String> destroy(@PathVariable long ingresso3D_id) {
		try {
			ingresso3DService.destroy(ingresso3D_id);
			return new ResponseEntity<>("Ingresso3D deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
