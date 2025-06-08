package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.AssentoSessaoCreateDTO;
import br.com.RollTickets.api.dto.AssentoSessaoResponseDTO;
import br.com.RollTickets.api.entity.AssentoSessao;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Sessao;

public class AssentoSessaoMapper {
      
	public static AssentoSessaoResponseDTO toDTO(AssentoSessao assentoSessao) {
		AssentoSessaoResponseDTO assentoSessaoResponse = new AssentoSessaoResponseDTO(assentoSessao.getId(),assentoSessao.getAssento(),assentoSessao.getSessao(),assentoSessao.getReservado());
		return assentoSessaoResponse;
	}
	
	public static AssentoSessao toEntity(AssentoSessaoCreateDTO assentoSessaoCreateDTO,Assento assento, Sessao sessao) { //Modifiqyuei para ele receber os objetos 
		AssentoSessao assentoSessao = new AssentoSessao();
		assentoSessao.setAssento(assento);
		assentoSessao.setSessao(sessao);
	
		return assentoSessao;
	}
}
