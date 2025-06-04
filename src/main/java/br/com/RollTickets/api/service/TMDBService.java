package br.com.RollTickets.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.enums.Formato;
import br.com.RollTickets.api.repository.FilmeRepository;

@Service
public class TMDBService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    private FilmeRepository filmeRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public void importarFilmesPopulares() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey + "&language=pt-BR&page=1";

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode results = response.getBody().get("results");

        for (JsonNode node : results) {
            String titulo = node.get("title").asText();
            String sinopse = node.get("overview").asText();
            int duracao = 180; // valor fictício, TMDB exige chamada separada para duração real
            String classificacao = "16"; // default, pois TMDB não fornece diretamente
            String imageUrl = "https://image.tmdb.org/t/p/w500" + node.get("poster_path").asText();
            Formato formato = Formato.DOIS_D;

            Filme filme = new Filme(titulo, sinopse, duracao, classificacao, imageUrl, formato);
            if (!filmeRepository.existsByTitulo(titulo)) {
                filmeRepository.save(filme);
            }
        }
    }

    @Scheduled(cron = "0 * * * * ?") // A cada minuto
    public void importarFilmesPopularesAgendado() {
        System.out.println("Realizando importação automática dos filmes do TMDB...");
        importarFilmesPopulares();
    }
}