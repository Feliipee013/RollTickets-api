package br.com.RollTickets.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.RollTickets.api.dto.AssentoSessaoCreateDTO;
import br.com.RollTickets.api.dto.AssentoSessaoResponseDTO;
import br.com.RollTickets.api.dto.AssentoSessaoUpdateDTO;
import br.com.RollTickets.api.service.AssentoSessaoService;

@RestController
@RequestMapping("/api/assentoSessao")
public class AssentoSessaoController {

    @Autowired
    private AssentoSessaoService assentoSessaoService;

    @PostMapping("/reservar")
    public ResponseEntity<AssentoSessaoResponseDTO> store(@RequestBody AssentoSessaoCreateDTO assentoSessaoCreateDTO) {
        return new ResponseEntity<>(assentoSessaoService.store(assentoSessaoCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssentoSessaoResponseDTO>> list() {
        return new ResponseEntity<>(assentoSessaoService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id_assentoSessao}")
    public ResponseEntity<?> show(@PathVariable long id_assentoSessao) {
        try {
            return new ResponseEntity<>(assentoSessaoService.show(id_assentoSessao), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody AssentoSessaoUpdateDTO assentoSessaoUpdateDTO) {
        try {
            return new ResponseEntity<>(assentoSessaoService.update(assentoSessaoUpdateDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_assentoSessao}")
    public ResponseEntity<String> destroy(@PathVariable long id_assentoSessao) {
        try {
            assentoSessaoService.destroy(id_assentoSessao);
            return new ResponseEntity<>("Assento da Sessão deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


