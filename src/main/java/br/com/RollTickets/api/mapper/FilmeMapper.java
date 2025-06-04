package br.com.RollTickets.api.mapper;
import br.com.RollTickets.api.dto.FilmeCreateDTO;
import br.com.RollTickets.api.dto.FilmeResponseDTO;
import br.com.RollTickets.api.entity.Filme;

public class FilmeMapper {
	 public static FilmeResponseDTO toDTO(Filme filme) {
	        return new FilmeResponseDTO(
	            filme.getId(),
	            filme.getTitulo(),
	            filme.getSinopse(),
	            filme.getDuracao(),
	            filme.getClassificacao(),
	            filme.getImageUrl(),
	            filme.getFormato(),
				filme.getAvaliacao()
	        );
	    }

	    public static Filme toEntity(FilmeCreateDTO dto) {
	        return new Filme(
	            dto.titulo(),
	            dto.sinopse(),
	            dto.duracao(),
	            dto.classificacao(),
	            dto.imageUrl(),
	            dto.formato(),
				dto.avaliacao()
	        );
	    }
}
