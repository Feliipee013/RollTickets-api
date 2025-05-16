package br.com.RollTickets.api.mapper;
import br.com.RollTickets.api.dto.ClienteCreateDTO;
import br.com.RollTickets.api.dto.ClienteResponseDTO;
import br.com.RollTickets.api.dto.FilmeCreateDTO;
import br.com.RollTickets.api.dto.FilmeResponseDTO;
import br.com.RollTickets.api.dto.FilmeUpdateDTO;
import br.com.RollTickets.api.entity.Filme;

public class FilmeMapper {
    public static FilmeResponseDTO toDTO(Filme filme) {
		FilmeResponseDTO FilmeResponse = new FilmeResponseDTO(Filme.getId(), Filme.getTitulo(), Filme.getSinopse(), Filme.getDuracao(), Filme.getClassificacao(), Filme.getGenero(), Filme.getImageUrl(), Filme.getFormato());
		return FilmeResponse;
	}
	
	public static Filme toEntity(FilmeResponseDTO filmeResponseDTO) {
		Filme filme = new Filme();
		filme.getId(FilmeResponseDTO.id());
		filme.getTitulo(FilmeResponseDTO.titulo());
		filme.getSinopse(FilmeResponseDTO.sinopse());
		filme.setDuracao(FilmeResponseDTO.duracao());
		filme.getClassificacao(FilmeResponseDTO.classificacao());
        filme.getImageUrl(FilmeResponseDTO.imageUrl());
        filme.getFormato()(FilmeResponseDTO.formato());

		return filme;
	}
}
