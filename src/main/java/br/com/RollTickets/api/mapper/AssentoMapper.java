package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.AssentoCreateDTO;
import br.com.RollTickets.api.dto.AssentoResponseDTO;
import br.com.RollTickets.api.entity.Assento;

public class AssentoMapper {

    public static AssentoResponseDTO toDTO(Assento assento) {
        AssentoResponseDTO assentoResponse = new AssentoResponseDTO(assento.getId(), assento.getFileira(), assento.getNumero(), assento.getSala(),assento.getSessao());
        return assentoResponse;
    }

    public static Assento toEntity(AssentoCreateDTO assentoCreateDTO) {
        Assento assento = new Assento();
        assento.setFileira(assentoCreateDTO.fileira());
        assento.setNumero(assentoCreateDTO.numero());
        assento.setSala(assentoCreateDTO.sala());
        assento.setSessao(assentoCreateDTO.sessao());
        return assento;
    }
}
