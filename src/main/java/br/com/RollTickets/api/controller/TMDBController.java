package br.com.RollTickets.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.RollTickets.api.service.TMDBService;

@RestController
@RequestMapping("/api/tmdb")
public class TMDBController {

    @Autowired
    private TMDBService tmdbService;

    @PostMapping("/importar")
    public ResponseEntity<String> importarFilmes() {
        tmdbService.importarFilmesPopulares();
        return ResponseEntity.ok("Filmes importados com sucesso!");
    }
}