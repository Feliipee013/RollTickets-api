package br.com.RollTickets.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.RollTickets.api.dto.CompraCreateDTO;
import br.com.RollTickets.api.dto.CompraResponseDTO;
import br.com.RollTickets.api.dto.CompraUpdateDTO;
import br.com.RollTickets.api.service.CompraService;


@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;


    @PostMapping("/realizar")
    public ResponseEntity<CompraResponseDTO> store(@RequestBody CompraCreateDTO compraCreateDTO) {
        return new ResponseEntity<>(compraService.store(compraCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(compraService.show(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody CompraUpdateDTO compraUpdateDTO) {
        try {
            return new ResponseEntity<>(compraService.update(compraUpdateDTO), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id) {
        try {
            compraService.destroy(id);
            return new ResponseEntity<>("Compra deletada com sucesso", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
