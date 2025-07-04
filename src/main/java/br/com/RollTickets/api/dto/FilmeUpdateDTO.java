package br.com.RollTickets.api.dto;
import br.com.RollTickets.api.enums.Formato;

public record FilmeUpdateDTO (
		long id,
	    String titulo,
	    String sinopse,
	    int duracao,
	    String classificacao,
	    String imageUrl,
	    Formato formato,
		Double avalicao
){}

