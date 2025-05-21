package br.com.RollTickets.api.mapper;


import br.com.RollTickets.api.dto.SalaCreateDTO;
import br.com.RollTickets.api.dto.SalaResponseDTO;
import br.com.RollTickets.api.entity.Sala;

public class SalaMapper {

	public static SalaResponseDTO toDTO(Sala sala) {
		SalaResponseDTO salaResponse = new SalaResponseDTO(sala.getId(), sala.getNumero(), sala.getCapacidade());
		return salaResponse;
	}
	
	public static Sala toEntity(SalaCreateDTO salaCreateDTO) {
		Sala sala= new Sala();
		sala.setNumero(salaCreateDTO.numero());
		sala.setCapacidade(salaCreateDTO.capacidade());
		return sala;
	}
}
