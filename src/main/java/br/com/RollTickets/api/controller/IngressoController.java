package br.com.RollTickets.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.RollTickets.api.dto.IngressoCreateDTO;
import br.com.RollTickets.api.dto.IngressoResponseDTO;
import br.com.RollTickets.api.dto.IngressoUpdateDTO;
import br.com.RollTickets.api.service.IngressoService;

@RestController
@RequestMapping("/api/ingressos")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<IngressoResponseDTO> store(@RequestBody IngressoCreateDTO dto) {
        return new ResponseEntity<>(ingressoService.store(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IngressoResponseDTO>> list() {
        return new ResponseEntity<>(ingressoService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(ingressoService.show(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cliente/{clienteId}") //Serve para buscar os ingressos pelo id do cliente
    public ResponseEntity<List<IngressoResponseDTO>> getIngressosPorCliente(@PathVariable Long clienteId) {
        List<IngressoResponseDTO> ingressos = ingressoService.buscarIngressosPorCliente(clienteId);
        return ResponseEntity.ok(ingressos);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody IngressoUpdateDTO dto) {
        try {
            return new ResponseEntity<>(ingressoService.update(dto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id) {
        try {
            ingressoService.destroy(id);
            return new ResponseEntity<>("Ingresso deletado com sucesso", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
