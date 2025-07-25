package br.com.RollTickets.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.RollTickets.api.dto.FilmeCreateDTO;
import br.com.RollTickets.api.dto.FilmeResponseDTO;
import br.com.RollTickets.api.dto.FilmeUpdateDTO;
import br.com.RollTickets.api.service.FilmeService;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<FilmeResponseDTO> store(@RequestBody FilmeCreateDTO filmeCreateDTO) {
        return new ResponseEntity<>(filmeService.store(filmeCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FilmeResponseDTO>> list(@RequestParam(required = false) String titulo) {
        return new ResponseEntity<>(filmeService.list(titulo), HttpStatus.OK);
    }

    @GetMapping("/{filme_id}")
    public ResponseEntity<FilmeResponseDTO> show(@PathVariable("filme_id") long id) {
        try {
            return new ResponseEntity<>(filmeService.show(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    public ResponseEntity<FilmeResponseDTO> update(@RequestBody FilmeUpdateDTO filmeUpdateDTO) {
        try {
            return new ResponseEntity<>(filmeService.update(filmeUpdateDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{filme_id}")
    public ResponseEntity<String> destroy(@PathVariable("filme_id") long id) {
        try {
            filmeService.destroy(id);
            return new ResponseEntity<>("Filme deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
