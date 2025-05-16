package br.com.RollTickets.api.dto;
import br.com.RollTickets.api.enums.Formato;

public record FilmeCreateDTO (
    long id,
    String titulo,
    String sinopse,
    int duracao,
    String classificacao,
    String genero,
    String imageUrl,
    Formato formato
){}
