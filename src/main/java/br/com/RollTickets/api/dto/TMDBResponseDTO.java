package br.com.RollTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TMDBResponseDTO {

    @JsonProperty("results")
    private List<TMDBFilmeDTO> results;

    public List<TMDBFilmeDTO> getResults() {
        return results;
    }

    public void setResults(List<TMDBFilmeDTO> results) {
        this.results = results;
    }
}
