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

import br.com.RollTickets.api.dto.AssentoCreateDTO;
import br.com.RollTickets.api.dto.AssentoResponseDTO;
import br.com.RollTickets.api.dto.AssentoUpdateDTO;
import br.com.RollTickets.api.service.AssentoService;

@RestController
@RequestMapping("/api/assentos")
public class AssentoController {

    @Autowired
    private AssentoService assentoService;

    @PostMapping("/reservar")
    public ResponseEntity<List<AssentoResponseDTO>> store(@RequestBody List<AssentoCreateDTO> assentoCreateDTO) {
        List<AssentoResponseDTO> reservados = assentoService.storeAll(assentoCreateDTO);
        return new ResponseEntity<>(reservados, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssentoResponseDTO>> list() {
        return new ResponseEntity<>(assentoService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id_assento}")
    public ResponseEntity<?> show(@PathVariable long id) {
        try {
            return new ResponseEntity<>(assentoService.show(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody AssentoUpdateDTO assentoUpdateDTO) {
        try {
            return new ResponseEntity<>(assentoService.update(assentoUpdateDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reservados/{sessaoId}") //Serve para conseguir listar os assentos disponíveis no front
    public ResponseEntity<List<AssentoResponseDTO>> getAssentosReservados(@PathVariable Long sessaoId) {
        List<AssentoResponseDTO> reservados = assentoService.listBySessao(sessaoId);
        return new ResponseEntity<>(reservados, HttpStatus.OK);
    }

    @DeleteMapping("/{id_assento}")
    public ResponseEntity<String> destroy(@PathVariable long id_assento) {
        try {
            assentoService.destroy(id_assento);
            return new ResponseEntity<>("Assento deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
