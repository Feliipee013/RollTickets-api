package br.com.RollTickets.api.mapper;
import br.com.RollTickets.api.dto.FilmeCreateDTO;
import br.com.RollTickets.api.dto.FilmeResponseDTO;
import br.com.RollTickets.api.entity.Filme;

public class FilmeMapper {
    public static FilmeResponseDTO toDTO(Filme filme) {
		FilmeResponseDTO FilmeResponse = new FilmeResponseDTO(filme.getId(),filme.getTitulo(),filme.getSinopse(),filme.getDuracao(),filme.getClassificacao(),filme.getGenero(),filme.getImageUrl(),filme.getFormato());
		return FilmeResponse;
	}
	
	public static Filme toEntity(FilmeCreateDTO filmeCreateDTO) {
		Filme filme = new Filme();

		filme.setTitulo(filmeCreateDTO.titulo());
		filme.setSinopse(filmeCreateDTO.sinopse());
		filme.setDuracao(filmeCreateDTO.duracao());
		filme.setClassificacao(filmeCreateDTO.classificacao());
        filme.setImageUrl(filmeCreateDTO.imageUrl());
        filme.setFormato(filmeCreateDTO.formato()); 


		return filme;
	}
}
