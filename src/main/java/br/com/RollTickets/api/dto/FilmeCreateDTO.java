package br.com.RollTickets.api.dto;
import br.com.RollTickets.api.enums.Formato;

public record FilmeCreateDTO (
		 String titulo,
		    String sinopse,
		    int duracao,
		    String classificacao,
		    String imageUrl,
		    Formato formato,
			Double avaliacao
){}
