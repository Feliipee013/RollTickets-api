package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.AssentoCreateDTO;
import br.com.RollTickets.api.dto.AssentoResponseDTO;
import br.com.RollTickets.api.entity.Assento;

public class AssentoMapper {

    public static AssentoResponseDTO toDTO(Assento assento) {
<<<<<<< HEAD
        AssentoResponseDTO assentoResponse = new AssentoResponseDTO(assento.getId(), assento.getFileira(), assento.getNumero());
=======
        AssentoResponseDTO assentoResponse = new AssentoResponseDTO(assento.getId(), assento.getFileira(), assento.getNumero(), assento.getSala());
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
        return assentoResponse;
    }

    public static Assento toEntity(AssentoCreateDTO assentoCreateDTO) {
        Assento assento = new Assento();
        assento.setFileira(assentoCreateDTO.fileira());
        assento.setNumero(assentoCreateDTO.numero());
<<<<<<< HEAD
=======
        assento.setSala(assentoCreateDTO.sala());
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
        return assento;
    }
}
