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

import br.com.RollTickets.api.dto.SessaoCreateDTO;
import br.com.RollTickets.api.dto.SessaoResponseDTO;
import br.com.RollTickets.api.dto.SessaoUpdateDTO;
import br.com.RollTickets.api.service.SessaoService;


@RestController
<<<<<<< HEAD
@RequestMapping("/sessoes")
=======
@RequestMapping("/api/sessoes")
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
public class SessaoController {
    @Autowired
	SessaoService sessaoService;

    @PostMapping("/realizar")
	public ResponseEntity<SessaoResponseDTO> store(@RequestBody SessaoCreateDTO sessaoCreateDTO) {
		return new ResponseEntity<>(sessaoService.store(sessaoCreateDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<SessaoResponseDTO>> list()
	{
		return new ResponseEntity<>(sessaoService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{sessao_id}")
	public ResponseEntity<SessaoResponseDTO> show(@PathVariable long sessao_id) {
		try {
			return new ResponseEntity<>(sessaoService.show(sessao_id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<SessaoResponseDTO> update(@RequestBody SessaoUpdateDTO sessaoUpdateDTO) {
		try {
			return new ResponseEntity<>(sessaoService.update(sessaoUpdateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{sessao_id}")
	public ResponseEntity<String> destroy(@PathVariable long sessao_id) {
		try {
			sessaoService.destroy(sessao_id);
			return new ResponseEntity<>("Sessao deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
