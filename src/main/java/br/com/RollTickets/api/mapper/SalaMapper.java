package br.com.RollTickets.api.mapper;


import br.com.RollTickets.api.dto.SalaCreateDTO;
import br.com.RollTickets.api.dto.SalaResponseDTO;
import br.com.RollTickets.api.entity.Sala;

public class SalaMapper {

	public static SalaResponseDTO toDTO(Sala sala) {
<<<<<<< HEAD
		SalaResponseDTO salaResponse = new SalaResponseDTO(sala.getId(), sala.getNumero(), sala.getAssento(), sala.getCapacidade());
=======
		SalaResponseDTO salaResponse = new SalaResponseDTO(sala.getId(), sala.getNumero(), sala.getCapacidade());
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
		return salaResponse;
	}
	
	public static Sala toEntity(SalaCreateDTO salaCreateDTO) {
		Sala sala= new Sala();
<<<<<<< HEAD
		sala.setAssento(salaCreateDTO.asssento());
=======
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
		sala.setNumero(salaCreateDTO.numero());
		sala.setCapacidade(salaCreateDTO.capacidade());
		return sala;
	}
}
