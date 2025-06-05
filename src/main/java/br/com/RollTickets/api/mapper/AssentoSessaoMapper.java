package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.AssentoSessaoCreateDTO;
import br.com.RollTickets.api.dto.AssentoSessaoResponseDTO;
import br.com.RollTickets.api.entity.AssentoSessao;


public class AssentoSessaoMapper {
      
	public static AssentoSessaoResponseDTO toDTO(AssentoSessao assentoSessao) {
		AssentoSessaoResponseDTO assentoSessaoResponse = new AssentoSessaoResponseDTO(assentoSessao.getId(),assentoSessao.getAssento(),assentoSessao.getSessao(),assentoSessao.getReservado());
		return assentoSessaoResponse;
	}
	
	public static AssentoSessao toEntity(AssentoSessaoCreateDTO assentoSessaoCreateDTO) {
		AssentoSessao assentoSessao = new AssentoSessao();
		assentoSessao.setAssento(assentoSessaoCreateDTO.assento());
		assentoSessao.setSessao(assentoSessaoCreateDTO.sessao());
		assentoSessao.setReservado(assentoSessaoCreateDTO.reservado());
	
		return assentoSessao;
	}
}
